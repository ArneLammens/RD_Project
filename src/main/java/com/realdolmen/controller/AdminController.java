package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.country.CountryService;
import com.realdolmen.domain.trip.TripService;
import com.realdolmen.session.CountrySession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by BPTAT47 on 8/10/2014.
 */
@Named
@ViewScoped
public class AdminController implements Serializable{

    @Inject
    private CountryService countryService;

    @Inject
    private CountrySession countrySession;

    private Enums.Region region;

    @Inject
    private Logger logger;

    @Inject
    private TripService tripService;

    private List<List>retrievedList;


    @Inject
    Event<FacesMessage> eventMessage;

    private List<Country> approvedCountries = new ArrayList<>();
    private List<Country> disapprovedCountries = new ArrayList<>();
    private DualListModel<Country> dualListModel = new DualListModel<>();

    public void init(){

    }

    public void getCountryOfDepartureRegion(AjaxBehaviorEvent event) {
        /*DEREFERENCE OTHERWISE THE LISTS STILL HAVE THE SAME REFERENCE AND UPDATES ARE DONE AUTOMATICALLY*/
        logger.info("get approved countries for departure region: "+region.getLabel());
        retrievedList = new ArrayList<List>(countrySession.getCorrectCountryListForAdmin(region));
        approvedCountries = new ArrayList<Country>(retrievedList.get(0));
        disapprovedCountries = new ArrayList<Country>(retrievedList.get(1));
        dualListModel.setSource(approvedCountries);
        dualListModel.setTarget(disapprovedCountries);
    }
    public Enums.Region[] getRegions() {
        return Enums.Region.values();
    }

    public void onTransfer(TransferEvent event) {
        for(Object item : event.getItems()) {
            logger.info("" + item);
            Country country = (Country) item;
           if((approvedCountries.contains(country))){
               if(tripService.checkTripsExistForGivenCountry(country)){
                   eventMessage.fire(new Message().info("Country already has trips" + country.getName()));
               } else{
                   approvedCountries.remove(country);
                   country.setApproved(false);
                   disapprovedCountries.add(country);
               }
           }else {
               disapprovedCountries.remove(country);
               country.setApproved(true);
               approvedCountries.add(country);
           }
        }
        eventMessage.fire(new Message().info("Country was transferred"));
    }

    public String saveCountryList(){
        countryService.persistAllowedAndDisallowedCountries(approvedCountries,disapprovedCountries);
        countrySession.updateSessionForGivenRegion(region, approvedCountries, disapprovedCountries);
        return RedirectEnum.REDIRECT.INDEX.getUrl();
    }

    public List<Country> getApprovedCountries() {
        return approvedCountries;
    }

    public void setApprovedCountries(List<Country> approvedCountries) {
        this.approvedCountries = approvedCountries;
    }

    public List<Country> getDisapprovedCountries() {
        return disapprovedCountries;
    }

    public void setDisapprovedCountries(List<Country> disapprovedCountries) {
        this.disapprovedCountries = disapprovedCountries;
    }

    public Enums.Region getRegion() {
        return region;
    }

    public void setRegion(Enums.Region region) {
        this.region = region;
    }

    public DualListModel<Country> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<Country> dualListModel) {
        this.dualListModel = dualListModel;
    }
}
