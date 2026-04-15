package com.example.T2507e.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUser {
    @NotBlank(message = "Please enter your name")
    private String fullName;

    @NotBlank(message = "Please enter your email")
    private String email;

    @Min(value = 6)
    private String password;
}
