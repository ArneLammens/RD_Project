package com.realdolmen.domain.trip;

import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.person.Person;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ALMAU78 on 9/10/2014.
 */
public class TripDTO
{

    private Integer id;
    @NotNull
    @Length(min = 1)
    private String name;
    @ManyToOne
    @NotNull
    private Flight departureFlight;
    @ManyToOne
    @NotNull
    private Flight returnFlight;
    @ManyToOne
    @NotNull
    private Person travelAgent;
    @NotNull
    @Min(1)
    private int numberOfSeats;
    @Min(0)
    private int availableSeats;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date endDate;

    private BigDecimal price;

    public TripDTO(Integer id, String name, Flight departureFlight, Flight returnFlight, Person travelAgent, int numberOfSeats, int availableSeats, Date startDate, Date endDate, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.departureFlight = departureFlight;
        this.returnFlight = returnFlight;
        this.travelAgent = travelAgent;
        this.numberOfSeats = numberOfSeats;
        this.availableSeats = availableSeats;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public TripDTO() {
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

    public Flight getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(Flight departureFlight) {
        this.departureFlight = departureFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public Person getTravelAgent() {
        return travelAgent;
    }

    public void setTravelAgent(Person travelAgent) {
        this.travelAgent = travelAgent;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
