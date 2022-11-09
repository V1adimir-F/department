package com.department.service.impl;

import com.department.dto.request.EmployeeRequestDTO;
import com.department.dto.response.AllEmployeeResponseDTO;
import com.department.dto.response.EmployeeInfo;
import com.department.dto.response.EmployeeResponseDTO;
import com.department.exception.BadRequestException;
import com.department.model.Employee;
import com.department.model.Post;
import com.department.model.Rank;
import com.department.model.Status;
import com.department.repository.EmployeeRepository;
import com.department.repository.PostRepository;
import com.department.repository.RankRepository;
import com.department.service.EmployeeService;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Vladimir F. 13.09.2022 13:37
 */
 
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RankRepository rankRepository;
    private final PostRepository postRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RankRepository rankRepository, PostRepository postRepository) {
        this.employeeRepository = employeeRepository;
        this.rankRepository = rankRepository;
        this.postRepository = postRepository;
    }

    @Override
    public AllEmployeeResponseDTO getEmployeesOnPage(@Nullable Long rankId,
                                                     @Nullable Long postId,
                                                     Integer pageNumber,
                                                     Integer countOnPage) throws BadRequestException {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, countOnPage, sort);
        Page<Employee> employeePage = getEmployeePage(pageRequest, rankId, postId);

        List<EmployeeInfo> employeeInfoList = employeePage
                .stream()
                .map(EmployeeInfo::fromEmployee)
                .toList();

        long countOfEmployees = getCountOfEmployees(rankId, postId);

        return AllEmployeeResponseDTO.fromEmployeeInfo(employeeInfoList, countOfEmployees);
    }

    private long getCountOfEmployees(@Nullable Long rankId, @Nullable Long postId) {
        if (rankId != null && postId != null) {
            Rank rank = getRankById(rankId);
            Post post = getPostById(postId);
            return employeeRepository.countAllByRankAndPost(rank, post);
        } else if (rankId != null) {
            Rank rank = getRankById(rankId);
            return employeeRepository.countAllByRank(rank);
        } else if (postId != null) {
            Post post = getPostById(postId);
            return employeeRepository.countAllByPost(post);
        } else {
            return employeeRepository.count();
        }
    }

    private Page<Employee> getEmployeePage(PageRequest pageRequest, @Nullable Long rankId, @Nullable Long postId) throws BadRequestException {
        if (rankId != null && postId != null) {
            Rank rank = getRankById(rankId);
            Post post = getPostById(postId);
            return employeeRepository.findAllByRankAndPost(pageRequest, rank, post);
        } else if (rankId != null) {
            Rank rank = getRankById(rankId);
            return employeeRepository.findAllByRank(pageRequest, rank);
        } else if (postId != null) {
            Post post = getPostById(postId);
            return employeeRepository.findAllByPost(pageRequest, post);
        } else {
            return employeeRepository.findAll(pageRequest);
        }
    }

    private Rank getRankById(Long rankId) throws BadRequestException {
        return rankRepository
                .findById(rankId)
                .orElseThrow(() -> new BadRequestException("Incorrect data for fetch employee"));
    }

    private Post getPostById(Long postId) throws BadRequestException {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new BadRequestException("Incorrect data for fetch employee"));
    }

    @Override
    public EmployeeResponseDTO getOneEmployee(Long employeeId) throws BadRequestException {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new BadRequestException("Incorrect data for fetch employee"));

        return EmployeeResponseDTO.fromEmployee(employee);
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) throws BadRequestException {
        if (employeeRepository.existsByPersonalNumber(employeeRequestDTO.personalNumber())) {
            throw new BadRequestException("Employee with personal number " + employeeRequestDTO.personalNumber() + " already exists");
        }
        // TODO: 06.11.2022 проверку на валидность данных вынести в отдельный метод и добавить проверку в updateEmployee
        if (employeeRequestDTO.surname() == null || employeeRequestDTO.surname().isEmpty() ||
                employeeRequestDTO.firstname() == null || employeeRequestDTO.firstname().isEmpty() ||
                employeeRequestDTO.middleName() == null || employeeRequestDTO.middleName().isEmpty() ||
                employeeRequestDTO.birthday() == null || employeeRequestDTO.birthday().toString().isEmpty() ||
                employeeRequestDTO.personalNumber() == null) {
            throw new BadRequestException("Not enough data for creating employee");
        }
        Rank rank = rankRepository
                .findById(employeeRequestDTO.rankId())
                .orElseThrow(() -> new BadRequestException("Incorrect data for creating employee"));
        Post post = postRepository
                .findById(employeeRequestDTO.postId())
                .orElseThrow(() -> new BadRequestException("Incorrect data for creating employee"));
        Employee employee = new Employee();
        // TODO: 06.11.2022 заполнение данных об employee вынести в отдельный метод
        employee.setSurname(employeeRequestDTO.surname());
        employee.setFirstname(employeeRequestDTO.firstname());
        employee.setMiddleName(employeeRequestDTO.middleName());
        employee.setBirthday(employeeRequestDTO.birthday());
        employee.setPersonalNumber(employeeRequestDTO.personalNumber());
        employee.setRank(rank);
        employee.setPost(post);
        employee.setCreated(LocalDateTime.now());
        employee.setUpdated(LocalDateTime.now());
        employee.setStatus(Status.ACTIVE);
        employeeRepository.save(employee);
        return EmployeeResponseDTO.fromEmployee(employee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, Long employeeId) throws BadRequestException {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new BadRequestException("Incorrect data for updating employee"));
        Rank rank = rankRepository
                .findById(employeeRequestDTO.rankId())
                .orElseThrow(() -> new BadRequestException("Incorrect data for updating employee"));
        Post post = postRepository
                .findById(employeeRequestDTO.postId())
                .orElseThrow(() -> new BadRequestException("Incorrect data for updating employee"));

        Optional<Employee> sameEmployee = employeeRepository.findByPersonalNumber(employeeRequestDTO.personalNumber());
        if (sameEmployee.isPresent() && !sameEmployee.get()
                .equals(employee)) {
            throw new BadRequestException("Employee with personal number " + employeeRequestDTO.personalNumber() + " already exists");
        }

        employee.setSurname(employeeRequestDTO.surname());
        employee.setFirstname(employeeRequestDTO.firstname());
        employee.setMiddleName(employeeRequestDTO.middleName());
        employee.setBirthday(employeeRequestDTO.birthday());
        employee.setPersonalNumber(employeeRequestDTO.personalNumber());
        employee.setRank(rank);
        employee.setPost(post);
        employee.setUpdated(LocalDateTime.now());
        employeeRepository.save(employee);
        return EmployeeResponseDTO.fromEmployee(employee);
    }

    @Override
    public EmployeeResponseDTO deleteEmployee(Long employeeId) throws BadRequestException {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new BadRequestException("Employee not exist"));
        employeeRepository.delete(employee);
        return EmployeeResponseDTO.fromEmployee(employee);
    }
}
