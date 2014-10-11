package com.realdolmen.domain.company;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.trip.TripRepository;
import com.realdolmen.domain.trip.TripService;
import com.realdolmen.util.Message;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CompanyService {
    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private TripService tripService;
    @Inject
    private Event<FacesMessage> event;



    public List<Company> getAllCompanies(Enums.RolesForACompany rolesForACompany){
        return companyRepository.getAllCompanies(rolesForACompany);
    }

    public boolean removeCompany(Company company)
    {
       if(tripService.checkTripsExistThatUseFlightOfGivenCompany(company))
       {
           return false;
       }
        else
       {
           companyRepository.removeCompany(company);
            return true;
       }
    }

}
