package com.department.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vladimir F. 07.11.2022 19:17
 */
 
public record AllEmployeeResponseDTO(List<EmployeeInfo> rows,
                                     long count) implements Serializable {

    public static AllEmployeeResponseDTO fromEmployeeInfo(List<EmployeeInfo> employeeInfoList, long count) {
        return new AllEmployeeResponseDTO(
                employeeInfoList,
                count
        );
    }
}
