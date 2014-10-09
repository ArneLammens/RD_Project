package com.realdolmen.controller;

import com.realdolmen.domain.trip.Trip;
import com.realdolmen.session.LoginSession;
import com.realdolmen.util.RedirectEnum;
import org.slf4j.Logger;


import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Named
@ViewScoped
public class BookingController implements Serializable {
    @Inject
    private Logger logger;

    @Inject
    private LoginSession loginSession;

    private Trip trip;

    public String init() {
        logger.info("BookingController init function");
         trip=(Trip)getCurrentInstance().getExternalContext().getFlash().get("trip");
        if(!loginSession.loggedIn()||trip==null )
        {
           return RedirectEnum.REDIRECT.INDEX.getUrl();
        }
          return null;

    }




}
