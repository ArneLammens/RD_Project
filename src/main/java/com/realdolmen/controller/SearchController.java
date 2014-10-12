package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.booking.Booking;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.company.CompanyService;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.flight.FlightPeriod;
import com.realdolmen.session.CountrySession;
import com.realdolmen.session.LoginSession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;
import com.realdolmen.util.ValidationUtil;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.realdolmen.util.ValidationUtil.validateForNotNullValues;

/**
 * Created by BPTAT47 on 11/10/2014.
 */
@Named
@ViewScoped
public class SearchController  implements Serializable{

    @Inject
    private CountrySession countrySession;

    private static Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

    @Inject
    private CompanyService companyService;

    @Inject
    private LoginSession loginSession;
    private FlightPeriod flightPeriod;
    private Enums.Region departureRegion;
    private Enums.Region destinationRegion;
    private List<Country> departureCountryList;
    private List<Country> destinationCountryList;
    private List<Company> companiesForFlightAdmin;
    private List<Company> companiesForTravelAdmin;
    private Company companyForFlightAdmin;
    private Company companyForTravelAdmin;
    private List<Booking> bookings;

    public String init()
    {
        if(loginSession.getLogin() == null)
        {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
        else if(loginSession.getLogin().getRole()!= Enums.Roles.ADMIN&&loginSession.getLogin().getRole()!= Enums.Roles.FLIGHT_ADMIN)
        {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }else
        {
            companiesForFlightAdmin = companyService.getAllCompanies(Enums.RolesForACompany.FLIGHT_ADMIN);
            companiesForTravelAdmin = companyService.getAllCompanies(Enums.RolesForACompany.TRAVEL_ADMIN);
            flightPeriod = new FlightPeriod();
            return null;
        }

    }

    public String getReportData(){
        if(validateForNotNullValues("search.noStartDate",flightPeriod.getStartDate(),"search.noEndDate",flightPeriod.getEndDate(),"search.noDepartureRegion",departureRegion,"search.noDestinationRegion",destinationRegion,"search.noCompanyTravelAgent",companyForTravelAdmin)||validator())
        {
            return RedirectEnum.REDIRECT.SEARCH.getUrl();
        }else
        {
            flash.put("flightPeriod" ,flightPeriod);
            flash.put("departureRegion" ,departureRegion);
            flash.put("destinationRegion" ,destinationRegion);
            flash.put("companyForFlightAdmin" ,companyForFlightAdmin);
            flash.put("companyForTravelAdmin" ,companyForTravelAdmin);
            return RedirectEnum.REDIRECT.REPORT.getUrl();
        }

    }
    public boolean validator()
    {
        if(flightPeriod!=null && flightPeriod.getStartDate()!=null&& flightPeriod.getEndDate()!=null)
        {
            if(loginSession.getLogin().getRole()==Enums.Roles.ADMIN)
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "search.noFlightCompany"));
                return true;
            }else if(flightPeriod.getStartDate().getTime()>flightPeriod.getEndDate().getTime())
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "search.startDateIsAfterEndDate"));
                return true;
            }
            else
            {
                return false;
            }
        }else
        {
            return true;
        }

    }

    public Enums.Region[] getRegions() {
        return Enums.Region.values();
    }

    public void getCountryOfDepartureRegion(AjaxBehaviorEvent event) {
        departureCountryList = countrySession.getCorrectCountryListForAGivenRegion(departureRegion);
    }

    public void getCountryOfDestinationRegion(AjaxBehaviorEvent event) {
        destinationCountryList = countrySession.getCorrectCountryListForAGivenRegion(destinationRegion);
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

    public FlightPeriod getFlightPeriod() {
        return flightPeriod;
    }

    public void setFlightPeriod(FlightPeriod flightPeriod) {
        this.flightPeriod = flightPeriod;
    }

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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Country> getDepartureCountryList() {
        return departureCountryList;
    }

    public void setDepartureCountryList(List<Country> departureCountryList) {
        this.departureCountryList = departureCountryList;
    }

    public List<Country> getDestinationCountryList() {
        return destinationCountryList;
    }

    public void setDestinationCountryList(List<Country> destinationCountryList) {
        this.destinationCountryList = destinationCountryList;
    }

    public List<Company> getCompaniesForFlightAdmin() {
        return companiesForFlightAdmin;
    }

    public void setCompaniesForFlightAdmin(List<Company> companiesForFlightAdmin) {
        this.companiesForFlightAdmin = companiesForFlightAdmin;
    }

    public List<Company> getCompaniesForTravelAdmin() {
        return companiesForTravelAdmin;
    }

    public void setCompaniesForTravelAdmin(List<Company> companiesForTravelAdmin) {
        this.companiesForTravelAdmin = companiesForTravelAdmin;
    }
}
