package com.example.demo.dto;
import com.example.demo.domain.User;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class SessionUser implements Serializable {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone_num;
    private String nickname;
    private int logged_in;
    private String profile_img;


    public SessionUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.phone_num = user.getPhone_num();
        this.nickname = user.getNickname();
        this.logged_in = user.getLogged_in();
        this.profile_img = user.getProfile_img();
    }
}
