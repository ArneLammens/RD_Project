package com.realdolmen.domain.login;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;

import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
public class Login implements Serializable {
    private Integer id;
    private String email;
    private String password;
    private Enums.Roles role;
    private Country country;
    private Enums.Region region;

    public Login() {
    }

    public Login(Integer id,String email, String password, Enums.Roles role) {
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
