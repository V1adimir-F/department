package com.department.dto.request;

import java.io.Serializable;

/**
 * @author Vladimir F. 22.09.2022 14:42
 */
 
public record PaymentRequestDTO(Long userId, Integer amount, Long purposeId) implements Serializable {
}
