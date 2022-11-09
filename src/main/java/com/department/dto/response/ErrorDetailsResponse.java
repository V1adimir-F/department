package com.department.dto.response;

import java.io.Serializable;

/**
 * @author Vladimir F. 20.09.2022 13:53
 */
 
public record ErrorDetailsResponse(String errorMessage) implements Serializable {

}
