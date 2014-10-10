package com.realdolmen.controller;

import com.realdolmen.domain.booking.Booking;
import com.realdolmen.domain.booking.BookingRepository;
import com.realdolmen.domain.booking.BookingService;
import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.person.PersonService;
import com.realdolmen.domain.trip.Trip;
import com.realdolmen.domain.trip.TripDTO;
import com.realdolmen.session.LoginSession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;
import org.slf4j.Logger;


import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Named
@ViewScoped
public class BookingController implements Serializable {
    @Inject
    private Logger logger;

    @Inject
    private LoginSession loginSession;

    @Inject
    private PersonService personService;

    @Inject
    private Event<FacesMessage> event;

    @Inject
    private BookingService bookingService;

    private TripDTO trip;
    private Person person;

    private String paymentMethode="";
    private boolean discount;

    private BigDecimal price;
    private BigDecimal priceWithDiscount;
    private int numberOfTickets;



    public String init() {
        logger.info("BookingController init function");

        if(!loginSession.loggedIn()||getCurrentInstance().getExternalContext().getFlash().get("trip")==null||getCurrentInstance().getExternalContext().getFlash().get("numberOfSeats")==null )
        {
           return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
        else
        {
            trip=(TripDTO)getCurrentInstance().getExternalContext().getFlash().get("trip");
            numberOfTickets = (Integer)getCurrentInstance().getExternalContext().getFlash().get("numberOfSeats");
            person = personService.findAPerson(loginSession.getLogin().getId());
            price= trip.getPrice();
            priceWithDiscount=(trip.getPrice().multiply(new BigDecimal(0.90))).setScale(2,BigDecimal.ROUND_HALF_UP);
            return null;
        }


    }

    public void alterPrice(AjaxBehaviorEvent event)
    {
        if(paymentMethode.equals("Creditcard")&& !discount) {
            trip.setPrice(priceWithDiscount);
            discount=true;
        }
        else if(discount)
        {
            trip.setPrice(price);
            discount=false;
        }
    }

    public String redirectToThankYouPage()
    {
        if(paymentMethode.equals(""))
        {
            event.fire(new Message().error("You must choose a payment methode"));
            return null;
        }
        else
        {
            logger.info("tripid "+trip.getId());
            logger.info("trip price "+trip.getPrice());
            logger.info("person id "+person.getId());
            logger.info("amount of tickets "+numberOfTickets);
            logger.info("booking service "+bookingService);

            bookingService.createBooking(trip.getId(),trip.getPrice(),person.getId(),numberOfTickets);
            return RedirectEnum.REDIRECT.THANK_YOU.getUrl();
        }

    }
    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPaymentMethode() {
        return paymentMethode;
    }

    public void setPaymentMethode(String paymentMethode) {
        this.paymentMethode = paymentMethode;
    }
}
