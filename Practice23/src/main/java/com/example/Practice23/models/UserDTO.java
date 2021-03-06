package com.example.Practice23.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDTO {
    @NotNull
    @NotEmpty
    private String username;;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
}
