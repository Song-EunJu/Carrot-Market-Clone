package com.example.demo.dto;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class SessionUser implements Serializable {
    private String email;
    private String password;

    public SessionUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
