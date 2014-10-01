package com.realdolmen.domain.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
public class Company {

    @GeneratedValue
    @Id
    private Integer id;
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
