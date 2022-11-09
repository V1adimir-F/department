package com.department.service;

import com.department.dto.request.EmployeeRequestDTO;
import com.department.dto.response.AllEmployeeResponseDTO;
import com.department.dto.response.EmployeeResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vladimir F. 13.09.2022 13:37
 */
 
public interface EmployeeService {

    AllEmployeeResponseDTO getEmployeesOnPage(Long rankId, Long postId, Integer pageNumber, Integer countOnPage);

    EmployeeResponseDTO getOneEmployee(Long employeeId);

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, Long employeeId);

    EmployeeResponseDTO deleteEmployee(Long employeeId);
}
