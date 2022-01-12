package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    @Column
    private Long user_id;

    @Column(nullable = false)
    private String user_email;

    @Column(nullable = false)
    private String user_password;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String user_phone_num;

    @Column(nullable = false)
    private String user_nickname;

    @Column(nullable = false)
    private String user_loggedIn;

    private String user_profile_img;

    @Builder
    public User(String user_email, String user_password, String user_name, String user_phone_num, String user_nickname, String user_loggedIn, String user_profile_img) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_phone_num = user_phone_num;
        this.user_nickname = user_nickname;
        this.user_loggedIn = user_loggedIn;
        this.user_profile_img = user_profile_img;
    }
}
