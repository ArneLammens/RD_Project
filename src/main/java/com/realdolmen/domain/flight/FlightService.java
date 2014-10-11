package com.realdolmen.domain.flight;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.trip.TripRepository;
import com.realdolmen.domain.trip.TripService;
import com.realdolmen.util.Message;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class FlightService
{
    @Inject
    private FlightRepository flightRepository;
    @Inject
    private TripService tripService;

    @Inject
    private Event<FacesMessage> event;


    public List<Flight> getFlightsForGivenCountry(Country country){
        return flightRepository.getFlightsForGivenCountry(country);
    }

    public void removeFlightForGivenCountry(List<Country> countryToBeRemoved){
         flightRepository.removeFlightForGivenCountry(countryToBeRemoved);
    }

    public List<Flight> getAllFlightsForGivenCompanyName(Company company){
       return flightRepository.getAllFlightsForGivenCompanyName(company);
    }

    public void createFlight(Flight flight)
    {
        flightRepository.persist(flight);
    }


    public void updateAFlight(Flight flight) {
        flightRepository.updateAFlight(flight);
    }


    public Flight findById(Integer id) {
        return flightRepository.findById(id);

    }
    public List<Flight>getAllFLightsForGivenRegionAndCompany(Enums.Region region,Person person)
    {
       return flightRepository.getAllFLightsForGivenRegionAndCompany(region,person);
    }

    public void removeFlight(Flight flight)
    {
        if(tripService.checkForExsistingTripsForGivenFLight(flight))
        {
            event.fire(new Message().warning( "You can not remove this flight because it is used in a trip"));
        }
        else
        {
            flightRepository.remove(flight.getId());
        }
    }
}
