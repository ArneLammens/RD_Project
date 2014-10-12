package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.flight.FlightPeriod;
import com.realdolmen.domain.flight.FlightService;
import com.realdolmen.domain.location.Location;
import com.realdolmen.domain.location.LocationService;
import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.person.PersonService;
import com.realdolmen.domain.trip.TripService;
import com.realdolmen.session.LoginSession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.realdolmen.util.ValidationUtil.validateForNotNullValues;

@Named
@ViewScoped
public class FlightAdminController implements Serializable
{
    @Inject
    private LocationService locationService;

    @Inject
    private FlightService flightService;

    @Inject
    private TripService tripService;

    @Inject
    private LoginSession loginSession;

    @Inject
    private PersonService personService;

    @Inject
    Event<FacesMessage> event;


    private Location departureLocation;
    private Location destinationLocation;
    private Person person;
    private Flight flight;
    private List<Flight> flights = new ArrayList<>();
    private List<Location>locations;
    private Date startDate;
    private Date endDate;
    private Date duration;
    private Date mindate=new Date();
    private Enums.DayOfTheWeek dayOfTheWeek;

    private Enums.Region region;

    public String init() {
        if( loginSession.getLogin()==null||loginSession.getLogin().getRole()!= Enums.Roles.FLIGHT_ADMIN)
        {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
        else
        {
            locations=locationService.getLocationsOfAprovedCountries();
            person=personService.findAPerson(loginSession.getLogin().getId());
            flight = new Flight();
            return null;
        }


    }
    public String initManageFlight()
    {
        if( loginSession.getLogin()==null||loginSession.getLogin().getRole()!= Enums.Roles.FLIGHT_ADMIN)
        {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
        else
        {
            regions();
            person=personService.findAPerson(loginSession.getLogin().getId());
            return null;
        }

    }

    public void getFlightForRegionAndCompany(AjaxBehaviorEvent event)
    {
        flights=flightService.getAllFLightsForGivenRegionAndCompany(region,person);
    }

    public String createFlight() {
        if(validateForNotNullValues("createFlight.noFlightNumber",flight.getFlightNumber(), "createFlight.noDeparture", flight.getDeparture(),
                "createFlight.noDestination", flight.getDestination(), "createFlight.noSeats", flight.getSeats(),
                "createFlight.noDiscountPercentage", flight.getDiscountPercentage(), "createFlight.noSeatThreshold", flight.getSeatThreshold(),"createFlight.noPrice",flight.getPrice(),"createFlight.noDepartureTime",flight.getDeparture(),"createFlight.noDuration",duration,"createFlight.noStartDate",startDate,"createFlight.noEndDate",endDate,"createFlight.noDayOfTheWeek",dayOfTheWeek )||validator()) {
            return RedirectEnum.REDIRECT.CREATE_FLIGHT.getUrl();
        }else {


            flight.setPeriod(new FlightPeriod(startDate, endDate));
            flight.setAvailableSeats(flight.getSeats());
            flight.setFlightAdmin(person);
            flight.setMargin(5);
            flight.setDateOfArrival(new Date(flight.getDepartureTime().getTime() + duration.getTime()));
            flight.setDayOfTheWeek(dayOfTheWeek);
            flight.setDiscountPercentage(flight.getDiscountPercentage() * 100);
            flightService.createFlight(flight);
            locations = null;
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }

    }
    public boolean validator()
    {
        if(flight.getDeparture()!=null||flight.getDestination()!=null||endDate!=null||startDate!=null)
        {
            if(flight.getDeparture().getId()==flight.getDestination().getId())
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.departureEqualsDestination"));
                return true;
            }else if (endDate.getTime() < startDate.getTime())
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.startDateIsAfterEndDate"));
                return true;
            }else if (endDate.getTime()+86399999l<new Date().getTime())
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.endDateBeforeToday"));
                return true;
            }else if(flight.getSeats()<2)
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.seatsLessThenTwo"));
                return true;

            }else if(flight.getDiscountPercentage()<0.00)
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.discountPercentageNegative"));
                return true;

            }else if(flight.getSeatThreshold()<0)
            { FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.seatThresholdNegative"));
                return true;

            }else if(flight.getPrice().signum()==-1)
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.priceNegative"));
                return true;

            }else if(flight.getFlightNumber().trim().isEmpty())
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createFlight.noFlightNumber"));
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


    public void getFlightsForGivenCompanyName(AjaxBehaviorEvent event){
        flights = flightService.getAllFlightsForGivenCompanyName(person.getCompany());
    }
    public void removeFlight(Flight flight)
    {
        if(flightService.removeFlight(flight))
        {
            event.fire(new Message().error("This flight can't be removed because it is used in a trip"));
        }
        else
        {

            flights.remove(flight);
            event.fire(new Message().info("The flight has been removed"));
        }


    }


    public Enums.DayOfTheWeek[] getweekDays() {
        return Enums.DayOfTheWeek.values();
    }
    public Enums.Region[] regions() {
        return Enums.Region.values();
    }
    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(Location destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Location departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getMindate() {
        return mindate;
    }

    public void setMindate(Date mindate) {
        this.mindate = mindate;
    }

    public Enums.DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(Enums.DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
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

    public Enums.Region getRegion() {
        return region;
    }

    public void setRegion(Enums.Region region) {
        this.region = region;
    }
}
