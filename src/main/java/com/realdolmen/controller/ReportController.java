package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.booking.Booking;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.flight.FlightPeriod;
import com.realdolmen.domain.search.SearchService;
import com.realdolmen.util.FlashUtil;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BPTAT47 on 11/10/2014.
 */
@Named
@ViewScoped
public class ReportController implements Serializable {
    @Inject
    private SearchService searchService;
    private Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    private FlightPeriod flightPeriod;
    private Enums.Region departureRegion;
    private Enums.Region destinationRegion;
    private Company companyForFlightAdmin;
    private Company companyForTravelAdmin;
    private List<Booking> bookings;

    public void init(){
        getAllValuesFromFlash();
        bookings = searchService.getAllReportData(flightPeriod,departureRegion,destinationRegion,companyForFlightAdmin,companyForTravelAdmin);
    }

    public void getAllValuesFromFlash(){
        flightPeriod = (FlightPeriod) flash.get("flightPeriod");
        departureRegion = (Enums.Region) flash.get("departureRegion");
        destinationRegion = (Enums.Region) flash.get("destinationRegion");
        companyForFlightAdmin = (Company)  flash.get("companyForFlightAdmin");
        companyForTravelAdmin = (Company)  flash.get("companyForTravelAdmin");
    }

    public FlightPeriod getFlightPeriod() {
        return flightPeriod;
    }

    public void setFlightPeriod(FlightPeriod flightPeriod) {
        this.flightPeriod = flightPeriod;
    }

    public Enums.Region getDepartureRegion() {
        return departureRegion;
    }

    public void setDepartureRegion(Enums.Region departureRegion) {
        this.departureRegion = departureRegion;
    }

    public Enums.Region getDestinationRegion() {
        return destinationRegion;
    }

    public void setDestinationRegion(Enums.Region destinationRegion) {
        this.destinationRegion = destinationRegion;
    }



    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
