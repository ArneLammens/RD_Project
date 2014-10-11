package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.company.CompanyService;
import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.flight.FlightPeriod;
import com.realdolmen.domain.flight.FlightService;

import com.realdolmen.util.Message;
import org.primefaces.event.CellEditEvent;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BPTAT47 on 9/10/2014.
 */
@Named
@ViewScoped
public class AdminFlightController implements Serializable{


    @Inject
    private CompanyService companyService;

    @Inject
    private FlightService flightService;

    @Inject
    Event<FacesMessage> eventMessage;
    private Company company;

    private Flight flight;

    List<Company> companies = new ArrayList<>();
    List<Flight> flights = new ArrayList<>();

    public void init() {

        companies = companyService.getAllCompanies();
        flight = new Flight();
    }

    public void getFlightsForGivenCompanyName(AjaxBehaviorEvent event){
       flights = flightService.getAllFlightsForGivenCompanyName(company);
    }

    public void onCellEdit(Flight flight) {
           Flight foundFlight = flightService.findById(flight.getId());
        if(flight != null && !flight.equals(foundFlight)) {
            flightService.updateAFlight(flight);
            eventMessage.fire(new Message().info( "The margin for " + foundFlight.getFlightNumber()  + "with margin" + foundFlight.getMargin() + "has been set to " + flight.getMargin()));
        }
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
