package com.realdolmen.domain.booking;

import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.trip.Trip;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
  private Trip trip;
  private BigDecimal totalPrice;
  @ManyToOne
  private Person travellingPerson;
  private int numberOfTickets;

    public Booking() {
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
