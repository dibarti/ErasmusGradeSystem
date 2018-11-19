package com.erasmus.grades.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    @Id
    private long userId;
    private String username;
    private String password;
    private int role;

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
