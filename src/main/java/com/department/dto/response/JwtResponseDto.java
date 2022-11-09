package com.department.dto.response;

/**
 * @author Vladimir F. 07.11.2022 16:45
 */
 
public class JwtResponseDto {

    private String token;
    private String tokenType = "Bearer_";

    public JwtResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
