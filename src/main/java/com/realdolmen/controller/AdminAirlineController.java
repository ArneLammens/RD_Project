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
public class AdminAirlineController implements Serializable {
    @Inject
    private CompanyService companyService;
    @Inject
    private LoginSession loginSession;
    @Inject
    private Event<FacesMessage> event;

    private List<Company>companies;

    public String init()
    {  if( loginSession.getLogin()==null||loginSession.getLogin().getRole()!= Enums.Roles.ADMIN)
    {
        return RedirectEnum.REDIRECT.INDEX.getUrl();
    }
    else
    {
       companies =companyService.getAllCompanies();
        return null;
    }
    }
    public void removeCompany(Company company)
    {
        companyService.removeCompany(company);
        event.fire(new Message().info("The company has been removed"));
    }

    public void refreshList(Company company)
    {
        companies.remove(company);
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
