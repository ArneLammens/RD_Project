package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.flight.FlightService;
import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.person.PersonService;
import com.realdolmen.session.LoginSession;
import com.realdolmen.util.RedirectEnum;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@ViewScoped
public class FlightAdminController implements Serializable
{
    @Inject
    private FlightService flightService;

    @Inject
    private Logger logger;

    @Inject
    private Event<FacesMessage> event;

    @Inject
    private LoginSession loginSession;

    @Inject
    private PersonService personService;

    private Flight flight;
    private Date duration;
    private Person person;


    public String init()
    {
        if(loginSession.getLogin().getRole()!= Enums.Roles.FLIGHT_ADMIN)
        {
            person=personService.findAPerson(loginSession.getLogin().getId());
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
       return null;
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
}
