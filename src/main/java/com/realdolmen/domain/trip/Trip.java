package com.realdolmen.domain.trip;

import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.person.Person;
import org.hibernate.validator.constraints.Length;

import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


@Entity
@NamedQuery(name = "Trip.getTripsForSearchData",query = "SELECT t FROM Trip t WHERE t.departureFlight.departure.country =:departureCountry AND t.returnFlight.departure.country  =:returnCountry AND :departureDate BETWEEN t.departureFlight.period.startDate AND t.departureFlight.period.endDate AND :returnDate BETWEEN t.returnFlight.period.startDate AND t.returnFlight.period.endDate AND t.availableSeats>= :numberOfSeats")
public class Trip implements Serializable {

    @Id @GeneratedValue
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


    public Trip() {
    }

    public Trip(String name, Flight departureFlight, Flight returnFlight, Person travelAgent, int numberOfSeats, int availableSeats, Date startDate, Date endDate) {
        this.name = name;
        this.departureFlight = departureFlight;
        this.returnFlight = returnFlight;
        this.travelAgent = travelAgent;
        this.numberOfSeats = numberOfSeats;
        this.availableSeats = availableSeats;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
