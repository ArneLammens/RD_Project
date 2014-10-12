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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class BookingRepository extends AbstractRepositoy<Booking> {
    public static final String GET_ALL_BOOKINGS = "where  b.trip.departureFlight.departure.country.region =:departureRegion and b.trip.departureFlight.destination.country.region =:destinationRegion and b.trip.startDate =:startDate and b.trip.endDate =:endDate  and b.trip.travelAgent.company =:travelAdmin and  b.trip.departureFlight.flightAdmin.company =:flightAdmin or b.trip.returnFlight.flightAdmin.company =:flightAdmin";
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

    public BigDecimal getAveragePriceFromBookings(FlightPeriod flightPeriod, Enums.Region departureRegion, Enums.Region destinationRegion, Company companyForFlightAdmin, Company companyForTravelAdmin) {

       return new BigDecimal ((Double) entityManager.createQuery("SELECT avg(b.totalPrice)FROM Booking b " + GET_ALL_BOOKINGS)
               .setParameter("startDate", flightPeriod.getStartDate())
               .setParameter("endDate", flightPeriod.getEndDate())
               .setParameter("flightAdmin", companyForFlightAdmin)
               .setParameter("travelAdmin", companyForTravelAdmin)
               .setParameter("departureRegion", departureRegion)
               .setParameter("destinationRegion", destinationRegion)
               .getSingleResult());

    }

    public BigDecimal getMaxPriceFromBookings(FlightPeriod flightPeriod, Enums.Region departureRegion, Enums.Region destinationRegion, Company companyForFlightAdmin, Company companyForTravelAdmin) {

        return (BigDecimal)entityManager.createQuery("SELECT MAX (b.totalPrice)FROM Booking b " + GET_ALL_BOOKINGS)
                .setParameter("startDate", flightPeriod.getStartDate())
                .setParameter("endDate", flightPeriod.getEndDate())
                .setParameter("flightAdmin", companyForFlightAdmin)
                .setParameter("travelAdmin", companyForTravelAdmin)
                .setParameter("departureRegion", departureRegion)
                .setParameter("destinationRegion", destinationRegion)
                .getSingleResult();

    }

    public BigDecimal getMinPriceFromBookings(FlightPeriod flightPeriod, Enums.Region departureRegion, Enums.Region destinationRegion, Company companyForFlightAdmin, Company companyForTravelAdmin) {

        return  (BigDecimal) entityManager.createQuery("SELECT MIN (b.totalPrice)FROM Booking b " + GET_ALL_BOOKINGS)
                .setParameter("startDate", flightPeriod.getStartDate())
                .setParameter("endDate", flightPeriod.getEndDate())
                .setParameter("flightAdmin", companyForFlightAdmin)
                .setParameter("travelAdmin", companyForTravelAdmin)
                .setParameter("departureRegion", departureRegion)
                .setParameter("destinationRegion", destinationRegion)
                .getSingleResult();

    }

    public BigDecimal getAverageMarginFromBookingsForDepartureFlight(FlightPeriod flightPeriod, Enums.Region departureRegion, Enums.Region destinationRegion, Company companyForFlightAdmin, Company companyForTravelAdmin) {

        return new BigDecimal ((Double)  entityManager.createQuery("SELECT AVG (b.trip.departureFlight.margin)FROM Booking b " + GET_ALL_BOOKINGS)
                .setParameter("startDate", flightPeriod.getStartDate())
                .setParameter("endDate", flightPeriod.getEndDate())
                .setParameter("flightAdmin", companyForFlightAdmin)
                .setParameter("travelAdmin", companyForTravelAdmin)
                .setParameter("departureRegion", departureRegion)
                .setParameter("destinationRegion", destinationRegion)
                .getSingleResult());

    }

  public BigDecimal getAverageMarginFromBookingsForReturnFlight(FlightPeriod flightPeriod, Enums.Region departureRegion, Enums.Region destinationRegion, Company companyForFlightAdmin, Company companyForTravelAdmin) {

        return new BigDecimal((Double) entityManager.createQuery("SELECT AVG (b.trip.returnFlight.margin)FROM Booking b " + GET_ALL_BOOKINGS)
                .setParameter("startDate", flightPeriod.getStartDate())
                .setParameter("endDate", flightPeriod.getEndDate())
                .setParameter("flightAdmin", companyForFlightAdmin)
                .setParameter("travelAdmin", companyForTravelAdmin)
                .setParameter("departureRegion", departureRegion)
                .setParameter("destinationRegion", destinationRegion)
                .getSingleResult());

    }

    public BigDecimal getAverageDiscountFromBookings(FlightPeriod flightPeriod, Enums.Region departureRegion, Enums.Region destinationRegion, Company companyForFlightAdmin, Company companyForTravelAdmin) {

        Object [] results = (Object []) entityManager.createQuery("SELECT AVG(b.trip.departureFlight.discountPercentage),AVG (b.trip.returnFlight.discountPercentage)FROM Booking b  " + GET_ALL_BOOKINGS)
                .setParameter("startDate", flightPeriod.getStartDate())
                .setParameter("endDate", flightPeriod.getEndDate())
                .setParameter("flightAdmin", companyForFlightAdmin)
                .setParameter("travelAdmin", companyForTravelAdmin)
                .setParameter("departureRegion", departureRegion)
                .setParameter("destinationRegion", destinationRegion)
                .getSingleResult();

       return  (new BigDecimal((Double)results[0]).add(new BigDecimal((Double)results[1]))).divide(new BigDecimal(2)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

}
