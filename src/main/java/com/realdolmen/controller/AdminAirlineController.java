package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.company.CompanyService;
import com.realdolmen.domain.flight.Flight;
import com.realdolmen.session.LoginSession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ALMAU78 on 10/10/2014.
 */
@Named
@ViewScoped
public class

        AdminAirlineController implements Serializable {
    @Inject
    private CompanyService companyService;
    @Inject
    private LoginSession loginSession;
    @Inject
    private Event<FacesMessage> event;

    private FacesContext context = FacesContext.getCurrentInstance();

    private List<Company>companies;

    public String init()
    {  if( loginSession.getLogin()==null||loginSession.getLogin().getRole()!= Enums.Roles.ADMIN)
    {
        return RedirectEnum.REDIRECT.INDEX.getUrl();
    }
    else
    {
       companies =companyService.getAllCompanies(Enums.RolesForACompany.FLIGHT_ADMIN);
       context.getExternalContext().getFlash().setKeepMessages(true);
        return null;
    }
    }
    public String removeCompany(Company company)
    {
      if( companyService.removeCompany(company)){
          companies.remove(company);
          context.addMessage(null, new Message().info("resourceBundle/ValidationMessages", "removeAirline.airlineIsRemoved",company.getName()));
          return RedirectEnum.REDIRECT.REMOVE_AIRLINE.getUrl();
      } else {
          event.fire(new Message().warning("resourceBundle/ValidationMessages", "removeAirline.canNotRemoveAirline", company.getName()));
          return " ";
      }
    }


    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
