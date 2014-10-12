package com.realdolmen.domain.flight;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.person.Person;
import com.realdolmen.util.Message;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Stateless
public class FlightRepository extends AbstractRepositoy<Flight> {

    @Inject
    private Event<FacesMessage> event;

    @Inject
    private Logger logger;
     public List<Flight> getFlightsForGivenCountry(Country country){
          return  entityManager.createNamedQuery("Flight.getFlightsForGivenCountry", Flight.class)
                 .setParameter("country", country).getResultList();
     }

    public void removeFlightForGivenCountry(List<Country> countries){
        for (Country country : countries) {
          entityManager.createQuery("DELETE FROM Flight f WHERE f.departure IN(SELECT d FROM f.departure d  WHERE d.country.id =:id ) or " +
                  "f.destination IN(SELECT d FROM f.destination d  WHERE d.country.id =:id )")
                  .setParameter("id",country.getId()).executeUpdate();

        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", "countryManagement.FlightsHaveBeenRemoved"));
   
    }


    public List<Flight> getAllFlightsForGivenCompanyName(Company company) {
      return   entityManager.createNamedQuery("Flight.getAllFlightsForGivenCompanyName",Flight.class)
              .setParameter("company",company)
              .getResultList()
              ;
    }

    public void updateAFlight(Flight flight){
        entityManager.merge(flight);
        entityManager.flush();
    }

    public Flight findById(Integer id) {
        return find(id);
    }
    public List<Flight>getAllFLightsForGivenRegionAndCompany(Enums.Region region,Person person)
    {
        return entityManager.createNamedQuery("Flight.getAllFLightsForGivenRegionAndCompany",Flight.class)
                .setParameter("company",person.getCompany())
                .setParameter("region",region)
                .getResultList();

    }
}
