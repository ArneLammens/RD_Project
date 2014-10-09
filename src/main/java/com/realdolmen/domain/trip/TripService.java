package com.realdolmen.domain.trip;

import com.realdolmen.domain.country.Country;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by BPTAT47 on 9/10/2014.
 */
@Stateless
public class TripService {

    @Inject
    private TripRepository tripRepository;

    public boolean checkTripsExistForGivenCountry(Country country){
         return tripRepository.checkTripsExistForGivenCountry(country);
    }
}
