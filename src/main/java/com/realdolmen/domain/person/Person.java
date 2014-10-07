package com.realdolmen.domain.person;

import com.realdolmen.domain.*;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.validator.ValidEmail;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ALMAU78 on 1/10/2014.
 */
@NamedQuery(name = "Person.retrievePersonWithGivenEmailAndPassword",query = "SELECT p FROM Person p WHERE p.email =:email and p.password  =:password")
@Entity
public class Person {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @ValidEmail
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
    private Enums.Roles role;
    @ManyToOne
    private Company company;

    public Person() {
    }


    public Person(String email, String password, String homeNumber, String zipCode, String municipality, Country country, Enums.Region region, String name, String lastName, Date dateOfBirth, Enums.Roles role) {
        this.email = email;
        this.password = password;
        this.homeNumber = homeNumber;
        this.zipCode = zipCode;
        this.municipality = municipality;
        this.country = country;
        this.region = region;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public Person(String email, String password, String homeNumber, String zipCode, String municipality, Enums.Region region, String name, String lastName, Date dateOfBirth, Enums.Roles role) {
        this.email = email;
        this.password = password;
        this.homeNumber = homeNumber;
        this.zipCode = zipCode;
        this.municipality = municipality;
        this.region = region;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public Person(String email, String password, String homeNumber, String zipCode, String municipality, Enums.Region region, String name, String lastName, Date dateOfBirth, Enums.Roles role, Company company) {
        this.email = email;
        this.password = password;
        this.homeNumber = homeNumber;
        this.zipCode = zipCode;
        this.municipality = municipality;
        this.region = region;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.company = company;
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

    public Enums.Roles getRole() {
        return role;
    }

    public void setRole(Enums.Roles role) {
        this.role = role;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
