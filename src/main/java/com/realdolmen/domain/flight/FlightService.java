package com.realdolmen.domain.flight;

import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class FlightService
{
    @Inject
    private FlightRepository flightRepository;


    public List<Flight> getFlightsForGivenCountry(Country country){
        return flightRepository.getFlightsForGivenCountry(country);
    }

    public void removeFlightForGivenCountry(List<Country> countryToBeRemoved){
         flightRepository.removeFlightForGivenCountry(countryToBeRemoved);
    }

    public List<Flight> getAllFlightsForGivenCompanyName(Company company){
       return flightRepository.getAllFlightsForGivenCompanyName(company);
    }


    public void updateAFlight(Flight flight) {
        flightRepository.updateAFlight(flight);
    }

    public Flight findById(Integer id){
      return flightRepository.findById(id);
    }
}
