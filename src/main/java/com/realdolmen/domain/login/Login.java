package com.realdolmen.domain.login;

import com.realdolmen.domain.Enums;

import javax.persistence.NamedQuery;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
public class Login {

    private String email;
    private String password;
    private Enums.Roles role;

    public Login() {
    }

    public Login(String email, String password, Enums.Roles role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enums.Roles getRole() {
        return role;
    }

    public void setRole(Enums.Roles role) {
        this.role = role;
    }
}
