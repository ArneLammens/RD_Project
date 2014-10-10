package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.company.CompanyService;
import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.flight.FlightService;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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

    private Company company;

    List<Company> companies = new ArrayList<>();
    List<Flight> flights = new ArrayList<>();

    public void init() {
        companies = companyService.getAllCompanies();
    }

    public void getFlightsForGivenCompanyName(AjaxBehaviorEvent event){
       flights = flightService.getAllFlightsForGivenCompanyName(company);
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
}
