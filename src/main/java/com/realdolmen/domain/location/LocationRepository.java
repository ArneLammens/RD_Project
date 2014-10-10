package com.realdolmen.domain.location;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.flight.Flight;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Stateless
public class LocationRepository extends AbstractRepositoy<Location>
{
    @Inject
    private Logger logger;

    public List<Location> getLocationsOfAprovedCountries()
    {
       /* return entityManager.createNamedQuery("Flight.getFlightsForGivenCountry", Flight.class)
                .setParameter("country", country).getResultList();*/
        return null;
    }
}
