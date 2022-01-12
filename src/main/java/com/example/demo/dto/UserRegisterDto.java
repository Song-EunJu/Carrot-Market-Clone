package com.example.demo.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    private String user_email;
    private String user_password;
    private String user_name;
    private String user_phone_num;
    private String user_nickname;
}
