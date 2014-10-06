package com.realdolmen.domain.person;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;

import java.util.Date;

/**
 * Created by ALMAU78 on 1/10/2014.
 */
public class PersonBuilder {
    private Integer id;
    private String email;
    private String password;
    private String homeNumber;
    private String zipCode;
    private String municipality;
    private Country country;
    private Enums.Region region;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private Enums.Roles role;
    private Company company;

    public static PersonBuilder aPerson(){
        return new PersonBuilder();
    }


    public PersonBuilder() {

    }

    public PersonBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    public PersonBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    public PersonBuilder withHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
        return this;
    }
    public PersonBuilder withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }
    public PersonBuilder withMunicipality( String municipality) {
        this.municipality = municipality;
        return this;
    }
    public PersonBuilder withCountry( Country country) {
        this.country = country;
        return this;
    }
    public PersonBuilder withRegion( Enums.Region region) {
        this.region = region;
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    public PersonBuilder withDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public PersonBuilder withRole(Enums.Roles role) {
        this.role = role;
        return this;
    }


    public Person build(){
        Person person = new Person(email,password,homeNumber, zipCode,  municipality,  country, region, name, lastName, dateOfBirth,role);
        person.setEmail(email);
        person.setPassword(password);
        person.setHomeNumber(homeNumber);
        person.setZipCode(zipCode);
        person.setMunicipality(municipality);
        person.setCountry(country);
        person.setRegion(region);
        person.setName(name);
        person.setLastName(lastName);
        person.setDateOfBirth(dateOfBirth);
        person.setRole(role);
        return person;
    }



}
