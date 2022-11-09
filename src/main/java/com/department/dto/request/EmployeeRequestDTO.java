package com.department.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Vladimir F. 13.09.2022 14:11
 */
 
public record EmployeeRequestDTO(String surname,
                                 String firstname,
                                 String middleName,
                                 LocalDate birthday,
                                 String personalNumber,
                                 Long rankId,
                                 Long postId) implements Serializable {
}
