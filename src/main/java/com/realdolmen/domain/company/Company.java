package com.realdolmen.domain.company;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
