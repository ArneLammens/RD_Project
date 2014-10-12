package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.person.PersonService;
import com.realdolmen.session.CountrySession;
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
import java.util.Date;
import java.util.List;
import static com.realdolmen.util.ValidationUtil.validateForNotNullValues;
import static com.realdolmen.util.ValidationUtil.validateStingsNotEmpty;

@Named
@ViewScoped
public class PersonController implements Serializable {

    public final static String regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
            + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
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


    public void init() {

        person = new Person();
        country = new Country();
    }

    public Enums.Region[] getRegions() {
        return Enums.Region.values();
    }

    public void getCountryOfPerson(AjaxBehaviorEvent event) {
        countries = countrySession.getCorrectCountryListForAGivenRegion(personRegion);
    }

    public String createAPerson() {
        if (validateForNotNullValues("createPeron.region", personRegion, "createPeron.country", person.getCountry(), "createPeron.dateOfBirth", person.getDateOfBirth()) ||
                validateStingsNotEmpty("createPeron.noName", person.getName(), "createPeron.noLastName", person.getLastName(), "createPeron.homeNumber", person.getHomeNumber(), "createPeron.municipality", person.getMunicipality(),"createPerson.street",person.getStreet(), "createPeron.zipCode", person.getZipCode()) ||
                validator())
        {
            return RedirectEnum.REDIRECT.CREATE_ACCOUNT.getUrl();
        } else {
            logger.info(person.getCountry().getName());
            person.setRole(Enums.Roles.USER);
            person.setRegion(getPersonRegion());
            personService.createAPerson(person);
            return RedirectEnum.REDIRECT.INDEX.getUrl();

        }

    }

    public boolean validator() {
        if (person.getDateOfBirth() != null) {
            if ((new Date().getTime()-person.getDateOfBirth().getTime()) < 441797328000l) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createPerson.minAgeForFLight"));
                return true;
            } else if (!person.getEmail().matches(regexp) || person.getEmail().trim().isEmpty()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createPerson.email"));
                return true;

            } else if (person.getPassword().trim().isEmpty() || person.getPassword().trim().length() < 5) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createPerson.password"));
                return true;
            }else if(personService.checkIfEmailAlreadyExists(person.getEmail()))
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "createPerson.emailAlreadyExists"));
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }


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
