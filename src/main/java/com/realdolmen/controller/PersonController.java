package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.person.PersonService;
import com.realdolmen.session.CountrySession;
import com.realdolmen.util.LoggerResources;
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
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PersonController implements Serializable{

    @Inject
    Event<FacesMessage> event;

    @Inject
    private Logger logger;

    @Inject
    private PersonService personService;

    private Person person;

    private Country country;

    @Inject
    private CountrySession countrySession;

    private Enums.Region personRegion;

    private List<Country> countries;


    public void init(){

        person = new Person();
        country = new Country();
    }

    public Enums.Region[] getRegions() {
        return Enums.Region.values();
    }
    public void getCountryOfPerson(AjaxBehaviorEvent event) {
        countries = countrySession.getCorrectCountryListForAGivenRegion(personRegion);
    }

   public String createAPerson(){
       logger.info(person.getCountry().getName());
       person.setRole(Enums.Roles.USER);
       person.setRegion(getPersonRegion());
       personService.createAPerson(person);
       return RedirectEnum.REDIRECT.INDEX.getUrl();
   }


 /*   public boolean validator()
    {
        if(country==null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("no country"));
            return true;
        }
        else if(personRegion == null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("no region"));
            return true;
        }
        else if(departureCountry== null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("no departure country"));
            return true;
        }
        else if(destinationCountry== null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("no destination country"));
            return true;
        } else if(departureDate== null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("no departure date"));
            return true;
        } else if(returnDate== null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("no return date"));
            return true;
        } else if(numberOfSeats== 0)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("no number of seats"));
            return true;
        }else if(returnDate.getTime()<= departureDate.getTime())
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("return date must be after departure date"));
            return true;
        }
        else {
            return false;
        }
    }*/





    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Enums.Region getPersonRegion() {
        return personRegion;
    }

    public void setPersonRegion(Enums.Region personRegion) {
        this.personRegion = personRegion;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


}
