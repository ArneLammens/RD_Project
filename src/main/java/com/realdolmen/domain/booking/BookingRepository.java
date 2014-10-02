package com.realdolmen.domain.booking;

import com.realdolmen.domain.AbstractRepositoy;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class BookingRepository extends AbstractRepositoy<Booking> {
}
