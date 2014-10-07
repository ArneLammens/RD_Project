package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.country.CountryRepository;
import com.realdolmen.session.CountrySession;
import org.slf4j.Logger;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Named
@ViewScoped
public class IndexController implements Serializable {
    /*resources from the server*/
    @Inject
    private Logger logger;
    @Inject
    private CountrySession countrySession;

    @Inject
    private CountryRepository countryRepositor;

    /*attributes and/or ejbs form server*/
    private Enums.Region destinationRegion;
    private Enums.Region departureRegion;
    private List<Country> departureCountryList;

    private Country departureCountry;
    private Country destinationCountry;
    private List<Country> destinationCountryList;

    private Date departureDate;
    private Date returnDate;
    private Date minDate =new Date() ;

    /*Functions for the index page*/
    public void init() {
        ResourceBundle bundle = ResourceBundle.getBundle("resourceBundle/Label", FacesContext.getCurrentInstance().getViewRoot().getLocale());

        logger.info("IndexController init function"+bundle.getString("index.search"));

    }

    public Enums.Region[] getRegions() {
        return Enums.Region.values();
    }

    public void getCountyOfDepartureRegion(AjaxBehaviorEvent event) {
        logger.info("get approved countries for departure region: "+departureRegion.getLabel());
        departureCountryList = countrySession.getCorrectCountryListForAGivenRegion(departureRegion);
    }

    public void getCountyOfDestinationRegion(AjaxBehaviorEvent event) {
        logger.info("get approved countries for destination region: "+destinationRegion.getLabel());
        destinationCountryList = countrySession.getCorrectCountryListForAGivenRegion(destinationRegion);
    }

    public String redirectToTripPage()
    {

        return "trips?faces-redirect=true";
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

    public List<Country> getDepartureCountryList() {
        return departureCountryList;
    }

    public void setDepartureCountryList(List<Country> departureCountryList) {
        this.departureCountryList = departureCountryList;
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

    public List<Country> getDestinationCountryList() {
        return destinationCountryList;
    }

    public void setDestinationCountryList(List<Country> destinationCountryList) {
        this.destinationCountryList = destinationCountryList;
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
