package com.example.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequest {

    @NotBlank(message = "first name is required")
    private String firstName;

    private String lastName;

    private String email;
}
