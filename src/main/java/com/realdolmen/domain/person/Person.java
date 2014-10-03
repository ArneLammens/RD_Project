package com.realdolmen.domain.person;

import com.realdolmen.domain.*;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.validator.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by ALMAU78 on 1/10/2014.
 */
@Entity
public class Person {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @Email
    private String email;
    @NotNull
    @Length(min=5)
    private String password;
    @NotNull
    @Length(min=1,max = 8)
    private String homeNumber;
    @NotNull
    @Length(min=1, max=9)
    private String zipCode;
    @NotNull
    @Length(min=1)
    private String municipality;
    @ManyToOne
    private Country country;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Enums.Region region;
    @NotNull
    @Length(min=1)
    private String name;
    @NotNull
    @Length(min = 1)
    private String lastName;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateOfBirth;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Enums.Roles roles;
    @ManyToOne
    private Company company;

    public Person() {
    }

    public Integer getId() {
        return id;
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

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Enums.Roles getRoles() {
        return roles;
    }

    public void setRoles(Enums.Roles roles) {
        this.roles = roles;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
