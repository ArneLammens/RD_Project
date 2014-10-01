package com.realdolmen.domain.trip;

import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.person.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
@Entity
public class Trip {
    @Id @GeneratedValue
    private Integer id;
    @ManyToOne
    private Flight departureFlight;
    @ManyToOne
    private Flight returnFlight;
    @ManyToOne
    private Person travelAgent;
    private int numberOfSeats;

    public Trip() {
    }

    public Integer getId() {
        return id;
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
}
