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


}
