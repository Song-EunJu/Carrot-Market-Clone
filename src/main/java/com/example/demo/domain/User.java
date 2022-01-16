package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    @Column
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone_num;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, columnDefinition = "TINYINT", length=1)
    private int logged_in;

    @Column
    private String profile_img;

    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {
        this.logged_in = this.logged_in == 1 ? 1: 0;
    }

    @Builder
    public User(String email, String password, String name, String phone_num, String nickname, String profile_img) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone_num = phone_num;
        this.nickname = nickname;
        this.profile_img = profile_img;
    }

}
