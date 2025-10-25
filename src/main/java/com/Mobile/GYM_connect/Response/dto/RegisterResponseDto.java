package com.Mobile.GYM_connect.Response.dto;

public class RegisterResponseDto {
    private String email;
    private String message;

    public RegisterResponseDto(String email, String message) {
        this.email = email;
        this.message = message;
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}