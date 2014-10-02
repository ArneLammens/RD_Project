package com.realdolmen.domain.flight;

import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.location.Location;
import com.realdolmen.domain.person.Person;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
public class Flight {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Length(min = 1)
    private String flightNumber;
    @ManyToOne
    @NotNull
    private Location departure;
    @ManyToOne
    @NotNull
    private Location destination;
    @NotNull
    @Min(2)
    private int seats;
    @NotNull
    private int availableSeats;
    @ManyToOne
    @NotNull
    private Company company;
    @Min(0)
    @Max(100)
    private double discountPercentage;
    @Min(0)
    private int seatThreshold;
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date departureTime;
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date dateOfArrival;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
    @Min(0)
    @Max(100)
    private double margin;
    @ManyToOne
    @NotNull
    private Person flightAdmin;

    public Flight() {
    }

    public Integer getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getSeatThreshold() {
        return seatThreshold;
    }

    public void setSeatThreshold(int seatThreshold) {
        this.seatThreshold = seatThreshold;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public Person getFlightAdmin() {
        return flightAdmin;
    }

    public void setFlightAdmin(Person flightAdmin) {
        this.flightAdmin = flightAdmin;
    }
}
