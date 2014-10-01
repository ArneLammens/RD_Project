package com.realdolmen.domain.booking;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BookingService
{
    @Inject
    private BookingRepository bookingRepository;
}
