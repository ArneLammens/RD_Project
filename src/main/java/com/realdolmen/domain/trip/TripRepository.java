package com.realdolmen.domain.trip;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.country.Country;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by BPTAT47 on 9/10/2014.
 */
@Stateless
public class TripRepository extends AbstractRepositoy<Trip>{

    public boolean checkTripsExistForGivenCountry(Country country){
        return (boolean)entityManager.createQuery("select case when (count(t) > 0)  then true else false end from Trip t where t.departureFlight.departure.country = :country or t.departureFlight.departure.country =:country ")
         .setParameter("country",country).getSingleResult();
    }
}
