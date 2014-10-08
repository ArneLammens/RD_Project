package com.realdolmen.domain.country;

import com.realdolmen.domain.Enums;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
@NamedQuery(name = "Country.getAllCountriesOfARegionApprovedOrDisapproved", query = "SELECT c FROM Country c WHERE c.region =:region and c.approved=:approved")
public class Country  {

    @GeneratedValue
    @Id
    private Integer id;
    @NotNull
    @Length(min=1)
    private String name;
    @NotNull
    private boolean approved;
    @Enumerated(EnumType.STRING)
    private Enums.Region region;

    public Country() {
    }

    public Country(String name, boolean approved, Enums.Region region) {
        this.name = name;
        this.approved = approved;
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (approved != country.approved) return false;
        if (!id.equals(country.id)) return false;
        if (!name.equals(country.name)) return false;
        if (region != country.region) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (approved ? 1 : 0);
        result = 31 * result + region.hashCode();
        return result;
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

    public Enums.Region getRegion() {
        return region;
    }

    public void setRegion(Enums.Region region) {
        this.region = region;
    }
}
