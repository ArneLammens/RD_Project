package com.realdolmen.domain.flight;

import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.location.Location;
import com.realdolmen.domain.person.Person;

import javax.persistence.*;
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
    private String flightNumber;
    @ManyToOne
    private Location departure;
    @ManyToOne
    private Location destination;
    private int seats;
    private int availableSeats;
    @ManyToOne
    private Company company;
    private double discountPercentage;
    private int seatThreshold;
    @Temporal(TemporalType.TIME)
    private Date departureTime;
    @Temporal(TemporalType.TIME)
    private Date dateOfArrival;
    private BigDecimal price;
    private double margin;
    @ManyToOne
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
