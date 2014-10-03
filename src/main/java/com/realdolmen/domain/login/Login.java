package com.realdolmen.domain.login;

import com.realdolmen.domain.Enums;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
public class Login {

    private String name;
    private String password;
    private Enums.Roles role;

    public Login() {
    }

    public Login(String name, String password, Enums.Roles role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
