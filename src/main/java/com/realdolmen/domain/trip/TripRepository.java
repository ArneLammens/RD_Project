package com.realdolmen.domain.trip;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.flight.Flight;

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
        List<Trip> trips =  entityManager.createQuery("select t from  Trip  t where t.departureFlight.destination.country = :country or t.departureFlight.departure.country =:country ")
                .setParameter("country",country).getResultList();
        if(trips.size() > 0){
            return true;
        }else{
            return false;
        }

    }
    public boolean checkTripsExistForGivenFlight(Flight flight){
        List<Trip> trips =  entityManager.createQuery("select t from  Trip  t where t.departureFlight= :flight or t.returnFlight =:flight ")
                .setParameter("flight",flight).getResultList();
        if(!trips.isEmpty()){
            return true;
        }else{
            return false;
        }

    }

    public boolean checkTripsExistThatUseFlightOfGivenCompany(Company company){
        List<Trip>trips= entityManager.createQuery("select t from  Trip  t where t.departureFlight.flightAdmin.company= :company or t.returnFlight.flightAdmin.company =:company ")
                .setParameter("company",company).getResultList();
        if(!trips.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

}
