package com.springboot.SattimSatiyorum.dto.Auth;


public class AuthRequestDTO {
    private String mail;
    private String password;

    public AuthRequestDTO(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println();
        this.password = password;
    }
}
