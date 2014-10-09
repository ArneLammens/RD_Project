package com.realdolmen.controller;


import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.trip.Trip;
import com.realdolmen.domain.trip.TripRepository;
import com.realdolmen.domain.trip.TripService;
import com.realdolmen.session.CountrySession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Named
@ViewScoped
public class TripController implements Serializable {
    /*resources from the server*/
    @Inject
    private Logger logger;
    @Inject
    private TripService tripService;
    @Inject
    Event<FacesMessage> event;

    private List<Trip>trips;
    private List<BigDecimal> price;


    public String init()
    {
        logger.info("init function tripcontroller");
        Country country=(Country)getCurrentInstance().getExternalContext().getFlash().get("departureCountry");
        Integer numberOfSeats=(Integer)getCurrentInstance().getExternalContext().getFlash().get("numberOfSeats");
        Date departureDate=(Date)getCurrentInstance().getExternalContext().getFlash().get("departureDate");
        Date returnDate=(Date)getCurrentInstance().getExternalContext().getFlash().get("returnDate");
        if(country==null || departureDate==null || returnDate==null || numberOfSeats==null)
        {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
        else
        {
            trips = tripService.getTripsForSearchData(country,
                    (Country) getCurrentInstance().getExternalContext().getFlash().get("destinationCountry"),
                    departureDate,
                    returnDate,
                    numberOfSeats);

            if(trips.isEmpty()){
                event.fire(new Message().warning("There are no trips"));
                return null;
            }else
            {
                price =tripService.getPriceForGivenTrips(trips, numberOfSeats, departureDate, returnDate);
                return null;
            }


        }

    }
    public String toLogin()
    {
        return RedirectEnum.REDIRECT.LOGIN.getUrl();
    }
    public String toBooking(Trip trip)
    {
        getCurrentInstance().getExternalContext().getFlash().put("Trip",trip);
        return RedirectEnum.REDIRECT.BOOKING.getUrl();
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
