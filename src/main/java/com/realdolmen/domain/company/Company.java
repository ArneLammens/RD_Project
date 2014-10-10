package com.realdolmen.domain.company;

import com.realdolmen.domain.Enums;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
public class Company {

    @GeneratedValue
    @Id
    private Integer id;
    @NotNull
    @Length(min = 2)
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Enums.RolesForACompany rolesForACompany;

    public Company() {
    }

    public Company(String name, Enums.RolesForACompany rolesForACompany) {
        this.name = name;
        this.rolesForACompany = rolesForACompany;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enums.RolesForACompany getRolesForACompany() {
        return rolesForACompany;
    }

    public void setRolesForACompany(Enums.RolesForACompany rolesForACompany) {
        this.rolesForACompany = rolesForACompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;

        Company company = (Company) o;

        if (!id.equals(company.id)) return false;
        if (!name.equals(company.name)) return false;
        if (rolesForACompany != company.rolesForACompany) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + rolesForACompany.hashCode();
        return result;
    }
}
