package com.diamondcode.common.application.port.in.model;

import com.diamondcode.common.application.service.validator.email.ValidEmail;
import com.diamondcode.common.application.service.validator.password.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDto {

    @NotEmpty
    @NotNull
    private   String name;

    @NotEmpty
    @NotNull
    @ValidEmail
    private  String email;

    @NotEmpty
    @NotNull
    private  String password;

    @NotEmpty
    @NotNull
    private  String matchingPassword;


}
