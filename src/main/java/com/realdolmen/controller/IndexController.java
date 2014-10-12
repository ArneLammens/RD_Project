package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.country.CountryRepository;
import com.realdolmen.domain.trip.TripService;
import com.realdolmen.session.CountrySession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;
import com.realdolmen.util.ValidationUtil;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static com.realdolmen.util.ValidationUtil.validateDatesForBeforeAndAfter;
import static com.realdolmen.util.ValidationUtil.validateForNotNullValues;
import static com.realdolmen.util.ValidationUtil.validateNumber;
import static javax.faces.context.FacesContext.getCurrentInstance;

@Named
@ViewScoped
public class IndexController implements Serializable {
    /*resources from the server*/
    @Inject
    private Logger logger;
    @Inject
    private CountrySession countrySession;
    @Inject
    private Event<FacesMessage> event;

    private Flash flash= FacesContext.getCurrentInstance().getExternalContext().getFlash();



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
    private int numberOfSeats;


    /*Functions for the index page*/
    public void init() {
        logger.info("IndexController init function"+departureDate);


    }

    public void getCountryOfDepartureRegion(AjaxBehaviorEvent event) {
        logger.info("get approved countries for departure region: "+departureRegion.getLabel());
        departureCountryList = countrySession.getCorrectCountryListForAGivenRegion(departureRegion);
    }

    public void getCountryOfDestinationRegion(AjaxBehaviorEvent event) {
        logger.info("get approved countries for destination region: "+destinationRegion.getLabel());
        destinationCountryList = countrySession.getCorrectCountryListForAGivenRegion(destinationRegion);
    }

    public String redirectToTripPage()
    {


        if(validateForNotNullValues("index.noDestinationRegion", destinationRegion, "index.noDepartureRegion", departureRegion,
                "index.noDestinationCountry", destinationCountry, "index.noDepartureCountry", departureCountry,
                "index.noDepartureDate", departureDate, "index.noReturnDate", returnDate,"index.noTicketsDate",numberOfSeats )||validator()) {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
        else {
            flash.put("destinationCountry", destinationCountry);
            flash.put("departureCountry", departureCountry);
            flash.put("departureDate", departureDate);
            flash.put("returnDate", returnDate);
            flash.put("numberOfSeats", numberOfSeats);

            return RedirectEnum.REDIRECT.TRIPS.getUrl();
        }
    }

    public boolean validator()
    {
        if(departureDate!=null||returnDate!=null) {
            if (numberOfSeats==0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.departureEqualsDestination"));
                return true;
            } else if (departureDate.getTime()>returnDate.getTime()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.startDateIsAfterEndDate"));
                return true;
            } else if (departureDate.getTime()+86399999l<new Date().getTime()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.endDateBeforeToday"));
                return true;
            } else {
                return false;
            }
        }
        else
        {
            return true;
        }
    }



    public Enums.Region[] getRegions() {
        return Enums.Region.values();
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

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
