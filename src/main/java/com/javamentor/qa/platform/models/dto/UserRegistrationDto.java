package com.javamentor.qa.platform.models.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
@Schema(description = "регистрация пользователя")
public class UserRegistrationDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public UserRegistrationDto(String firstName, String lastName, String email, String password) {
    }
}
