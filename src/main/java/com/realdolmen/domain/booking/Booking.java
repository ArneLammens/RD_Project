package com.realdolmen.domain.booking;

import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.trip.Trip;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
public class Booking {
  @GeneratedValue
  @Id
  private Integer id;
  @ManyToOne
  @NotNull
  private Trip trip;
  @NotNull
  @Min(1)
  private BigDecimal totalPrice;
  @ManyToOne
  @NotNull
  private Person travellingPerson;
    @NotNull
    @Min(1)
  private int numberOfTickets;

    public Booking() {
    }

    public Booking(Trip trip, BigDecimal totalPrice, Person travellingPerson, int numberOfTickets) {
        this.trip = trip;
        this.totalPrice = totalPrice;
        this.travellingPerson = travellingPerson;
        this.numberOfTickets = numberOfTickets;
    }

    public Integer getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Person getTravellingPerson() {
        return travellingPerson;
    }

    public void setTravellingPerson(Person travellingPerson) {
        this.travellingPerson = travellingPerson;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
