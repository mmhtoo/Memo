package com.mmhtoo.note.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginReqDTO {

    @NotNull( message = "Email is required!" )
    @NotEmpty( message = "Email is required!" )
    @Email( message = "Invalid email!" )
    private String email;

    @NotNull( message = "Password is required!" )
    @NotEmpty( message = "Password is required!" )
    private String password;

}
