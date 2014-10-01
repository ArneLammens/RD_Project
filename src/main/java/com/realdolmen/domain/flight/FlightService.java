package com.realdolmen.domain.flight;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FlightService
{
    @Inject
    private FlightRepository flightRepository;

}
