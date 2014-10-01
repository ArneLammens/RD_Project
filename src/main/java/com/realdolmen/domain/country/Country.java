package com.realdolmen.domain.country;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
public class Country {

    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private boolean approved;

    public Country() {
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Country(String name) {
        this.name = name;
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
