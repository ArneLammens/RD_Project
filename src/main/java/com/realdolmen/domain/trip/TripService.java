package com.realdolmen.domain.trip;

import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.flight.Flight;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

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
    public List<TripDTO>getPriceForGivenTrips(List<Trip>trips, int numberOfSeats)
    {
        List<TripDTO>DTOTrips=new ArrayList<>();

       for (Trip trip : trips) {
           TripDTO tempTrip = new TripDTO(trip.getId(),trip.getName(),trip.getDepartureFlight(),trip.getReturnFlight(),trip.getTravelAgent(),trip.getNumberOfSeats(),trip.getAvailableSeats(),trip.getStartDate(),trip.getEndDate(),calculatePriceForTrip(trip, numberOfSeats));
           DTOTrips.add(tempTrip);


        }
        return DTOTrips;
    }
    public boolean checkForExsistingTripsForGivenFLight(Flight flight)
    {
        return tripRepository.checkTripsExistForGivenFlight(flight);
    }
    public BigDecimal calculatePriceForTrip(Trip trip,int numberOfSeats)
    {
        logger.info("Calculate price for trip: "+trip.getName());
        BigDecimal departureFlightBasePriceWithMargin;
        BigDecimal returnFlightBasePriceWithMargin;
        int days=((int)( trip.getEndDate().getTime() - trip.getStartDate().getTime()) / (1000 * 60 * 60 * 24));
        BigDecimal amountOfDays = new BigDecimal(days);
        BigDecimal amountOfTickets=new BigDecimal(numberOfSeats);
        BigDecimal pricePerDay= trip.getDepartureFlight().getDestination().getPricePerDay();

        if(trip.getDepartureFlight().getSeatThreshold()<=trip.getNumberOfSeats())
        {
            BigDecimal discountPercentage = new BigDecimal(trip.getDepartureFlight().getDiscountPercentage());
            discountPercentage= discountPercentage.divide(new BigDecimal(100));
            discountPercentage=discountPercentage.add(new BigDecimal(1));

            BigDecimal subtotal= trip.getDepartureFlight().getPrice().multiply(discountPercentage);

            BigDecimal marginPercentage = new BigDecimal(trip.getDepartureFlight().getMargin());
            marginPercentage=marginPercentage.divide(new BigDecimal(100));
            marginPercentage=marginPercentage.add(new BigDecimal(1));

            departureFlightBasePriceWithMargin= subtotal.multiply(marginPercentage);
        }else
        {
            BigDecimal marginPercentage = new BigDecimal(trip.getDepartureFlight().getMargin());
            marginPercentage= marginPercentage.divide(new BigDecimal(100));
            marginPercentage=marginPercentage.add(new BigDecimal(1));

            departureFlightBasePriceWithMargin=trip.getDepartureFlight().getPrice().multiply(marginPercentage);
        }
        if(trip.getReturnFlight().getSeatThreshold()<=trip.getNumberOfSeats())
        {
            BigDecimal discountPercentage = new BigDecimal(trip.getReturnFlight().getDiscountPercentage());
            discountPercentage=discountPercentage.divide(new BigDecimal(100));
            discountPercentage=discountPercentage.add(new BigDecimal(1));

            BigDecimal subtotal= trip.getReturnFlight().getPrice().multiply(discountPercentage);

            BigDecimal marginPercentage = new BigDecimal(trip.getDepartureFlight().getMargin());
            marginPercentage= marginPercentage.divide(new BigDecimal(100));
            marginPercentage=marginPercentage.add(new BigDecimal(1));

            returnFlightBasePriceWithMargin= subtotal.multiply(marginPercentage);
        }
        else
        {
            BigDecimal marginPercentage = new BigDecimal(trip.getDepartureFlight().getMargin());
            marginPercentage=marginPercentage.divide(new BigDecimal(100));
            marginPercentage=marginPercentage.add(new BigDecimal(1));

            returnFlightBasePriceWithMargin=trip.getReturnFlight().getPrice().multiply(marginPercentage);
        }

        BigDecimal flightsTotalPrice = (departureFlightBasePriceWithMargin.add(returnFlightBasePriceWithMargin)).multiply(amountOfTickets);
        BigDecimal locationPricePerDay= (pricePerDay.multiply(amountOfDays)).multiply(amountOfTickets);


        return (flightsTotalPrice.add(locationPricePerDay)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public boolean checkTripsExistForGivenCountry(Country country){
        return tripRepository.checkTripsExistForGivenCountry(country);
    }
    public boolean checkTripsExistThatUseFlightOfGivenCompany(Company company)
    {
        return tripRepository.checkTripsExistThatUseFlightOfGivenCompany(company);
    }


}
