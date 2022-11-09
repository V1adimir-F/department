package com.department.dto.response;

import java.io.Serializable;

/**
 * @author Vladimir F. 22.09.2022 14:52
 */
 
public record PaymentResponseDTO(Long id, Long userId, Integer amount, Long purposeId) implements Serializable {
}
