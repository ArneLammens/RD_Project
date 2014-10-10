package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.flight.FlightService;
import com.realdolmen.session.LoginSession;
import com.realdolmen.util.RedirectEnum;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

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

    public String init()
    {
        if(loginSession.getLogin().getRole()!= Enums.Roles.FLIGHT_ADMIN)
        {
            return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
        return RedirectEnum.REDIRECT.CREATE_FLIGHT.getUrl();
    }
}
