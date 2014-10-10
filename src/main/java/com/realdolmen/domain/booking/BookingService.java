package com.realdolmen.domain.booking;

import com.realdolmen.domain.person.PersonRepository;
import com.realdolmen.domain.trip.TripRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;

@Stateless
public class BookingService
{
    @Inject
    private BookingRepository bookingRepository;

    @Inject
    private TripRepository tripRepository;

    @Inject
    private PersonRepository personRepository;


    public void createBooking(int tripid, BigDecimal price,int personid, int numberOfTickets)
    {

         bookingRepository.persist(new Booking(tripRepository.find(tripid),price,personRepository.find(personid),numberOfTickets));
    }
}
