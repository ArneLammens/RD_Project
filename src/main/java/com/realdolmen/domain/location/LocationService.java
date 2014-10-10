package com.realdolmen.domain.location;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LocationService
{
    @Inject
    private LocationRepository locationRepository;
}
