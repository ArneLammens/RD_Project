package com.realdolmen.controller;


import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.trip.Trip;
import com.realdolmen.domain.trip.TripDTO;
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

    private List<TripDTO>tripsWithPrice;
    private List<BigDecimal> price;
    private Integer numberOfSeats;


    public String init()
    {
        logger.info("init function tripcontroller");

        if(getCurrentInstance().getExternalContext().getFlash().get("departureCountry")==null || getCurrentInstance().getExternalContext().getFlash().get("numberOfSeats")==null || getCurrentInstance().getExternalContext().getFlash().get("departureDate")==null || getCurrentInstance().getExternalContext().getFlash().get("returnDate")==null)
        {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
        else
        {
            Country country=(Country)getCurrentInstance().getExternalContext().getFlash().get("departureCountry");
            numberOfSeats=(Integer)getCurrentInstance().getExternalContext().getFlash().get("numberOfSeats");
            Date departureDate=(Date)getCurrentInstance().getExternalContext().getFlash().get("departureDate");
            Date returnDate=(Date)getCurrentInstance().getExternalContext().getFlash().get("returnDate");
           List<Trip> trips = tripService.getTripsForSearchData(country,
                    (Country) getCurrentInstance().getExternalContext().getFlash().get("destinationCountry"),
                    departureDate,
                    returnDate,
                    numberOfSeats);

            if(trips.isEmpty()){
                event.fire(new Message().warning("There are no trips"));
                return null;
            }else
            {
                tripsWithPrice =tripService.getPriceForGivenTrips(trips, numberOfSeats);
                return null;
            }


        }

    }
    public String toLogin()
    {
        return RedirectEnum.REDIRECT.LOGIN.getUrl();
    }

    public String toBooking(TripDTO trip)
    {
        getCurrentInstance().getExternalContext().getFlash().put("trip",trip);
        getCurrentInstance().getExternalContext().getFlash().put("numberOfSeats",numberOfSeats);

        return RedirectEnum.REDIRECT.BOOKING.getUrl();
    }

    public List<TripDTO> getTripsWithPrice() {
        return tripsWithPrice;
    }

    public void setTripsWithPrice(List<TripDTO> tripsWithPrice) {
        this.tripsWithPrice = tripsWithPrice;
    }

    public List<BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(List<BigDecimal> price) {
        this.price = price;
    }
}
