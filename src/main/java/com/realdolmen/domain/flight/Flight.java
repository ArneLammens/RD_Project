package com.realdolmen.domain.flight;

import com.realdolmen.domain.Enums;
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
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name ="Flight.getFlightsForGivenCountry",query = "SELECT f FROM Flight f WHERE f.destination.country =:country or f.departure.country =:country"),
        @NamedQuery(name ="Flight.removeFlightsForGiveCountry",query = "DELETE FROM Flight f where f.id = 200"),
        @NamedQuery(name ="Flight.getAllFlightsForGivenCompanyName",query = "SELECT f FROM Flight  f WHERE f.flightAdmin.company =:company")
})
public class Flight{
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
    @Min(value = 2,message = "{person.email}")
    private int seats;
    @NotNull
    private int availableSeats;
    @Min(0)
    @Max(100)
    private double discountPercentage;
    @Min(0)
    private int seatThreshold;
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

    /*dates in flight working with period a flight flies every monday for a year at 12 a.m. for example*/
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date departureTime;
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date dateOfArrival;

    @Embedded
    @NotNull
    private FlightPeriod period;

    @Enumerated(EnumType.STRING)
    private Enums.DayOfTheWeek dayOfTheWeek;

    public Flight() {
    }

    public Flight(String flightNumber, Location departure, Location destination, int seats, int availableSeats, double discountPercentage, int seatThreshold, BigDecimal price, double margin, Person flightAdmin, Date departureTime, Date dateOfArrival, FlightPeriod period, Enums.DayOfTheWeek dayOfTheWeek) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.seats = seats;
        this.availableSeats = availableSeats;
        this.discountPercentage = discountPercentage;
        this.seatThreshold = seatThreshold;
        this.price = price;
        this.margin = margin;
        this.flightAdmin = flightAdmin;
        this.departureTime = departureTime;
        this.dateOfArrival = dateOfArrival;
        this.period = period;
        this.dayOfTheWeek = dayOfTheWeek;
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

    public FlightPeriod getPeriod() {
        return period;
    }

    public void setPeriod(FlightPeriod period) {
        this.period = period;
    }

    public Enums.DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(Enums.DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
