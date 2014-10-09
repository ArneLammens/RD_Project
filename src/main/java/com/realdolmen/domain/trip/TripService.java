package com.realdolmen.domain.trip;

import com.realdolmen.domain.country.Country;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class TripService {
    @Inject
    private TripRepository tripRepository;
    @Inject
    private Logger logger;


    public List<Trip> getTripsForSearchData(Country departureCountry, Country destinationCountry,Date departuredate, Date returnDate, int numberOfSeats)
    {
        return tripRepository.getTripsForSearchData(departureCountry,destinationCountry,departuredate,returnDate,numberOfSeats);
    }
    public List<BigDecimal>getPriceForGivenTrips(List<Trip>trips, int numberOfSeats,Date departureDate,Date returnDate)
    {
        List<BigDecimal>prices=new ArrayList<>();
        calculatePriceForTrip(trips.get(0),numberOfSeats,departureDate,returnDate);
   /*     for (Trip trip : trips) {

            prices.add();
        }*/

        return null;
    }
    public BigDecimal calculatePriceForTrip(Trip trip,int numberOfSeats,Date departureDate,Date returnDate)
    {


        logger.info("aantal dagen berekening "+  ((int)( (returnDate.getTime() - departureDate.getTime()) / (1000 * 60 * 60 * 24))));
        trip.getDepartureFlight().getPrice();
        trip.getReturnFlight().getPrice();
        trip.getDepartureFlight().getDestination().getPricePerDay();
        return null;
    }

}
