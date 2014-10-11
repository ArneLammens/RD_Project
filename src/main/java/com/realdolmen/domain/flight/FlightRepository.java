package com.realdolmen.domain.flight;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Stateless
public class FlightRepository extends AbstractRepositoy<Flight> {

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
            logger.info("removed " + country.getName());
        }
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
}
