package com.realdolmen.controller;

import com.realdolmen.controller.datamodel.LazyFlightModel;
import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.company.CompanyService;
import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.flight.FlightPeriod;
import com.realdolmen.domain.flight.FlightService;

import com.realdolmen.session.LoginSession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.LazyDataModel;

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

    @Inject
    private LoginSession loginSession;

    private Company company;

    private Flight flight;

    private List<Flight> filteredFlights;

    List<Company> companies = new ArrayList<>();

    private LazyDataModel<Flight> flights;

    public String init() {
        if(loginSession.getLogin()==null||loginSession.getLogin().getRole()!= Enums.Roles.ADMIN)
        {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }else
        {
            companies = companyService.getAllCompanies(Enums.RolesForACompany.FLIGHT_ADMIN);
            flight = new Flight();
            return null;
        }

    }

    public void getFlightsForGivenCompanyName(AjaxBehaviorEvent event){
        flights = new LazyFlightModel(flightService.getAllFlightsForGivenCompanyName(company));
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LazyDataModel<Flight> getFlights() {
        return flights;
    }

    public void setFlights(LazyDataModel<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFilteredFlights() {
        return filteredFlights;
    }

    public void setFilteredFlights(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
    }
}
