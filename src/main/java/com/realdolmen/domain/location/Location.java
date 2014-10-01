package com.realdolmen.domain.location;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
public class Location {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private Enums.Region region;
    @ManyToOne
    private Country country;
    private BigDecimal pricePerDay;

    public Location() {
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

    public Enums.Region getRegion() {
        return region;
    }

    public void setRegion(Enums.Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
