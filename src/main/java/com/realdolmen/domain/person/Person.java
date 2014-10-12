package com.realdolmen.domain.person;

import com.realdolmen.domain.*;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.validator.ValidEmail;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ALMAU78 on 1/10/2014.
 */
@NamedQuery(name = "Person.retrievePersonWithGivenEmailAndPassword",query = "SELECT p FROM Person p WHERE p.email =:email and p.password  =:password")
@Entity
public class Person implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String email;
    private String password;
    private String homeNumber;
    private String zipCode;
    private String municipality;
    @ManyToOne
    private Country country;
    @Enumerated(EnumType.STRING)
    private Enums.Region region;
    private String name;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
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

    public Person(String email, String password, String homeNumber, String zipCode, String municipality, Country country, Enums.Region region, String name, String lastName, Date dateOfBirth, Enums.Roles role, Company company) {
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
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (company != null ? !company.equals(person.company) : person.company != null) return false;
        if (!country.equals(person.country)) return false;
        if (!dateOfBirth.equals(person.dateOfBirth)) return false;
        if (!email.equals(person.email)) return false;
        if (!homeNumber.equals(person.homeNumber)) return false;
        if (!id.equals(person.id)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (!municipality.equals(person.municipality)) return false;
        if (!name.equals(person.name)) return false;
        if (!password.equals(person.password)) return false;
        if (region != person.region) return false;
        if (role != person.role) return false;
        if (!zipCode.equals(person.zipCode)) return false;

        return true;
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
