package com.department.dto.response;

import com.department.model.Employee;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Vladimir F. 13.09.2022 13:52
 */
 
public record EmployeeResponseDTO(Long id,
                                  String surname,
                                  String firstname,
                                  String middleName,
                                  LocalDate birthday,
                                  String personalNumber,
                                  String rankName,
                                  String postName) implements Serializable {

    public static EmployeeResponseDTO fromEmployee(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getSurname(),
                employee.getFirstname(),
                employee.getMiddleName(),
                employee.getBirthday(),
                employee.getPersonalNumber(),
                employee.getRank().getRankName(),
                employee.getPost().getPostName()
        );
    }
}
