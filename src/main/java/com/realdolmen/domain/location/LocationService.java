package com.realdolmen.domain.location;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LocationService
{
    @Inject
    private LocationRepository locationRepository;

    public List<Location>getLocationsOfAprovedCountries()
    {
       return locationRepository.getLocationsOfAprovedCountries();
    }
}
