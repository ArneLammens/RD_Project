package com.realdolmen.domain.booking;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.flight.FlightPeriod;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.print.Book;
import java.util.List;


@Stateless
public class BookingRepository extends AbstractRepositoy<Booking> {
    @Inject
    private Logger logger;

    public List<Booking> getAllReportData(FlightPeriod flightPeriod, Enums.Region departureRegion, Enums.Region destinationRegion, Company companyForFlightAdmin, Company companyForTravelAdmin) {
        List<Booking> bookings = entityManager.createNamedQuery("Booking.getAllBookings", Booking.class)
                .setParameter("startDate", flightPeriod.getStartDate())
                .setParameter("endDate", flightPeriod.getEndDate())
                .setParameter("flightAdmin", companyForFlightAdmin)
                .setParameter("travelAdmin", companyForTravelAdmin)
                .setParameter("departureRegion", departureRegion)
                .setParameter("destinationRegion", destinationRegion)
                .getResultList();
        logger.info("" + bookings.size());
        return bookings;
    }
}
