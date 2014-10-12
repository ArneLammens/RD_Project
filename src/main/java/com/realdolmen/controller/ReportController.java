package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.booking.Booking;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.flight.FlightPeriod;
import com.realdolmen.domain.search.SearchService;
import com.realdolmen.session.LoginSession;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by BPTAT47 on 11/10/2014.
 */
@Named
@ViewScoped
public class ReportController implements Serializable {
    @Inject
    private SearchService searchService;
    @Inject
    private LoginSession loginSession;
    private Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    private FlightPeriod flightPeriod;
    private Enums.Region departureRegion;
    private Enums.Region destinationRegion;
    private Company companyForFlightAdmin;
    private Company companyForTravelAdmin;
    private List<Booking> bookings;
    private BigDecimal averagePrice;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal marginAverage;

    public void init(){
        getAllValuesFromFlash();
        if( loginSession.getLogin().getRole() == Enums.Roles.ADMIN ){
            setAllQueriedValuesForAdmin();
        }else {
            setAllQueriedValuesForFlightAdmin();
        }
    }

    public void getAllValuesFromFlash(){
        flightPeriod = (FlightPeriod) flash.get("flightPeriod");
        departureRegion = (Enums.Region) flash.get("departureRegion");
        destinationRegion = (Enums.Region) flash.get("destinationRegion");
        companyForFlightAdmin = (Company)  flash.get("companyForFlightAdmin");
        companyForTravelAdmin = (Company)  flash.get("companyForTravelAdmin");
    }


    public void setAllQueriedValuesForAdmin(){
        bookings = searchService.getAllReportData(flightPeriod,departureRegion,destinationRegion,companyForFlightAdmin,companyForTravelAdmin);
        averagePrice = searchService.getAveragePriceFromBookings(flightPeriod,departureRegion,destinationRegion,companyForFlightAdmin,companyForTravelAdmin);
        averagePrice=averagePrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        maxPrice = searchService.getMaxPriceFromBookings(flightPeriod,departureRegion,destinationRegion,companyForFlightAdmin,companyForTravelAdmin);
        minPrice = searchService.getMinPriceFromBookings(flightPeriod,departureRegion,destinationRegion,companyForFlightAdmin,companyForTravelAdmin);
        marginAverage = searchService.getAverageMarginFromBookings(flightPeriod,departureRegion,destinationRegion,companyForFlightAdmin,companyForTravelAdmin);
    }

    public void setAllQueriedValuesForFlightAdmin(){
        bookings = searchService.getAllReportData(flightPeriod,departureRegion,destinationRegion, getCompanyFromLoginSession(),companyForTravelAdmin);
        averagePrice = searchService.getAveragePriceFromBookings(flightPeriod,departureRegion,destinationRegion,getCompanyFromLoginSession(),companyForTravelAdmin);
         averagePrice=averagePrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        maxPrice = searchService.getMaxPriceFromBookings(flightPeriod,departureRegion,destinationRegion,getCompanyFromLoginSession(),companyForTravelAdmin);
        minPrice = searchService.getMinPriceFromBookings(flightPeriod,departureRegion,destinationRegion,getCompanyFromLoginSession(),companyForTravelAdmin);
        marginAverage = searchService.getAverageMarginFromBookings(flightPeriod,departureRegion,destinationRegion,getCompanyFromLoginSession(),companyForTravelAdmin);
    }

    public Company getCompanyFromLoginSession(){
       return loginSession.getLogin().getCompany();
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

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getMarginAverage() {
        return marginAverage;
    }

    public void setMarginAverage(BigDecimal marginAverage) {
        this.marginAverage = marginAverage;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Company getCompanyForTravelAdmin() {
        return companyForTravelAdmin;
    }

    public void setCompanyForTravelAdmin(Company companyForTravelAdmin) {
        this.companyForTravelAdmin = companyForTravelAdmin;
    }

    public Company getCompanyForFlightAdmin() {
        return companyForFlightAdmin;
    }

    public void setCompanyForFlightAdmin(Company companyForFlightAdmin) {
        this.companyForFlightAdmin = companyForFlightAdmin;
    }
}
