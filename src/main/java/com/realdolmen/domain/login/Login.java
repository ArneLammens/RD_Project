package com.realdolmen.domain.login;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;

import java.io.Serializable;



public class Login implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Enums.Roles role;
    private Country country;
    private Enums.Region region;

    public Login() {
    }

    public Login(Integer id, String name, String email, String password, Enums.Roles role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Login(Integer id, String name, String email, String password, Enums.Roles role, Country country, Enums.Region region) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.country = country;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null?"":email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password == null?"":password;
    }

    public Enums.Roles getRole() {
        return role;
    }

    public void setRole(Enums.Roles role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Enums.Region getRegion() {
        return region;
    }

    public void setRegion(Enums.Region region) {
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
