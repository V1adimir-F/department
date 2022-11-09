package com.department.dto.response;

import com.department.model.Employee;

/**
 * @author Vladimir F. 08.11.2022 11:44
 */

public record EmployeeInfo(Long id,
                           String surname,
                           String firstname,
                           String middleName) {

    public static EmployeeInfo fromEmployee(Employee employee) {
        return new EmployeeInfo(
                employee.getId(),
                employee.getSurname(),
                employee.getFirstname(),
                employee.getMiddleName()
        );
    }
}
