package com.mvp.spotfind.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserProfileUpdateDto {
    @NotNull
    private String name;
    @NotNull @Length(min = 10, max=10)
    private String mobileNumber;
    @NotNull @Email
    private String email;
}