package com.mmhtoo.note.dto.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AccountRegisterReqDTO {

    @NotNull( message = "Username is required!")
    @NotEmpty( message = "Username is required!")
    @Size(min = 3,max = 20 , message = "Username should have at least 3 characters and 20 characters at most!")
    private String username;

    @NotNull( message = "Password is required!")
    @NotEmpty( message = "Password is required!")
    @Size(min = 6 , max = 20 , message = "Password should have at least 6 characters and 20 characters at most!")
    private String password;

    @NotNull( message = "Email is required!")
    @NotEmpty( message = "Email is required!")
    @Email( message = "Invalid email format!")
    @Size( min = 5 , max = 50 , message = "Email should have at least 5 characters and 50 characters!")
    private String email;

}
