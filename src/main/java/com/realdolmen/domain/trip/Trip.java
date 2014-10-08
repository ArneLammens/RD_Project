package com.realdolmen.domain.trip;

import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.person.Person;

import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.ResourceBundle;


@Entity
public class Trip {

    @Id @GeneratedValue
    private Integer id;
    @ManyToOne
    @NotNull
    private Flight departureFlight;
    @ManyToOne
    @NotNull(message = "{welcome.message}")
    private Flight returnFlight;
    @ManyToOne
    @NotNull
    private Person travelAgent;
    @NotNull
    @Min(1)
    private int numberOfSeats;

    public Trip() {
    }

    public Trip(Flight departureFlight, Flight returnFlight, Person travelAgent, int numberOfSeats) {
        this.departureFlight = departureFlight;
        this.returnFlight = returnFlight;
        this.travelAgent = travelAgent;
        this.numberOfSeats = numberOfSeats;
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
