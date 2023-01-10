package com.example.module6demo.model.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDTO {
    @Size(min = 6)
    private String userName;

    @Size(min = 6)
    private String password;
    private String confirmPassword;
    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)")
    private String phone;
    @Email
    private String email;
}
