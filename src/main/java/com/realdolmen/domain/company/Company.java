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
}
