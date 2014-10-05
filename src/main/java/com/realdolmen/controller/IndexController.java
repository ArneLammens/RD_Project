package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.country.CountryRepository;
import org.slf4j.Logger;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class IndexController implements Serializable {
    /*resources from the server*/
    @Inject
    private Logger logger;
    @Inject
    private CountryRepository countryRepository;

    /*attributes and/or ejbs form server*/
    private Enums.Region destinationRegion;
    private Enums.Region departureRegion;

    private List<Country> countryList;
    private Country departureCountry;
    private Country destinationCountry;

    private Date departureDate;
    private Date returnDate;
    private Date minDate =new Date() ;

    /*Functions for the index page*/
    public void init() {
        logger.info("IndexController init function");

    }

    public Enums.Region[] getRegions() {
        return Enums.Region.values();
    }

    public void getCountyOfDepartureRegion(AjaxBehaviorEvent event) {
        logger.info("get approved countries for departure region: "+departureRegion.getLabel());
        countryList = countryRepository.getAllApprovedCountriesOfARegion(departureRegion);
    }
    public void getCountyOfDestinationRegion(AjaxBehaviorEvent event) {
        logger.info("get approved countries for destination region: "+destinationRegion.getLabel());
        countryList = countryRepository.getAllApprovedCountriesOfARegion(destinationRegion);
    }

    /*Getters and Setters*/

    public Enums.Region getDestinationRegion() {
        return destinationRegion;
    }

    public void setDestinationRegion(Enums.Region destinationRegion) {
        this.destinationRegion = destinationRegion;
    }

    public Enums.Region getDepartureRegion() {
        return departureRegion;
    }

    public void setDepartureRegion(Enums.Region departureRegion) {
        this.departureRegion = departureRegion;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public Country getDepartureCountry() {
        return departureCountry;
    }

    public void setDepartureCountry(Country departureCountry) {
        this.departureCountry = departureCountry;
    }

    public Country getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(Country destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }
}
