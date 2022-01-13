package com.example.demo.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    private String email;
    private String password;
    private String name;
    private String phone_num;
    private String nickname;
}
