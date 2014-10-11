package com.realdolmen.domain.location;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@NamedQuery(name ="Location.getLocationsOfAprovedCountries",query = "SELECT l FROM Location  l WHERE l.country.approved =true")
public class Location {
    @Id @GeneratedValue
    private Integer id;
    @NotNull
    @Length(min = 1)
    private String name;
    @ManyToOne
    @NotNull
    private Country country;
    @NotNull
    @Min(0)
    private BigDecimal pricePerDay;

    public Location() {
    }

    public Location(String name, Country country, BigDecimal pricePerDay) {
        this.name = name;
        this.country = country;
        this.pricePerDay = pricePerDay;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (!country.equals(location.country)) return false;
        if (!id.equals(location.id)) return false;
        if (!name.equals(location.name)) return false;
        if (!pricePerDay.equals(location.pricePerDay)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + pricePerDay.hashCode();
        return result;
    }
}
