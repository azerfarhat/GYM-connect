package com.Mobile.GYM_connect.Response.dto;

public class LoginResponseDto {
    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}