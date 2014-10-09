package com.realdolmen.domain.trip;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.country.Country;

import javax.ejb.Stateless;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class TripRepository extends AbstractRepositoy<Trip>  {

    public List<Trip> getTripsForSearchData(Country departureCountry, Country destinationCountry,Date departuredate, Date returnDate, int numberOfSeats){
        return  entityManager.createNamedQuery("Trip.getTripsForSearchData", Trip.class)
                .setParameter("departureCountry",departureCountry )
                .setParameter("returnCountry", destinationCountry)
                .setParameter("departureDate",departuredate)
                .setParameter("returnDate",returnDate)
                .setParameter("numberOfSeats",numberOfSeats).getResultList();

    }

    public boolean checkTripsExistForGivenCountry(Country country){
        return (boolean)entityManager.createQuery("select case when (count(t) > 0)  then true else false end from Trip t where t.departureFlight.departure.country = :country or t.departureFlight.departure.country =:country ")
         .setParameter("country",country).getSingleResult();
    }

}
