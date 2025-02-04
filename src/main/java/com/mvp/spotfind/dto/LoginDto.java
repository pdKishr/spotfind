package com.mvp.spotfind.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginDto {
    private String mobileNumber;
    private String email ;
    private String password;

    public LoginDto(){

    }

    public LoginDto(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public LoginDto(String mobileNumber){
        this.mobileNumber = mobileNumber;
        this.password = password;

    }
}
