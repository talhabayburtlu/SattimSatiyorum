package com.springboot.SattimSatiyorum.dto.Auth;

public class AuthResponseDTO {

    private final String jwt;

    public AuthResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
