package com.realdolmen.domain.search;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.booking.Booking;
import com.realdolmen.domain.booking.BookingRepository;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.flight.FlightPeriod;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by BPTAT47 on 11/10/2014.
 */
@Stateless
public class SearchService {

    @Inject
    private BookingRepository bookingRepository;

    public List<Booking> getAllReportData(FlightPeriod flightPeriod,Enums.Region departureRegion,Enums.Region destinationRegion,Company companyForFlightAdmin,Company companyForTravelAdmin ){
          return bookingRepository.getAllReportData(flightPeriod,departureRegion,destinationRegion,companyForFlightAdmin,companyForTravelAdmin);

    }

    public BigDecimal getAveragePriceFromBookings(FlightPeriod flightPeriod,Enums.Region departureRegion,Enums.Region destinationRegion,Company companyForFlightAdmin,Company companyForTravelAdmin ){
        return bookingRepository.getAveragePriceFromBookings(flightPeriod, departureRegion, destinationRegion, companyForFlightAdmin, companyForTravelAdmin);

    }

    public BigDecimal getMaxPriceFromBookings(FlightPeriod flightPeriod,Enums.Region departureRegion,Enums.Region destinationRegion,Company companyForFlightAdmin,Company companyForTravelAdmin ){
        return bookingRepository.getMaxPriceFromBookings(flightPeriod, departureRegion, destinationRegion, companyForFlightAdmin, companyForTravelAdmin);
    }

    public BigDecimal getMinPriceFromBookings(FlightPeriod flightPeriod,Enums.Region departureRegion,Enums.Region destinationRegion,Company companyForFlightAdmin,Company companyForTravelAdmin ){
        return bookingRepository.getMinPriceFromBookings(flightPeriod, departureRegion, destinationRegion, companyForFlightAdmin, companyForTravelAdmin);
    }
    public BigDecimal getAverageMarginFromBookings(FlightPeriod flightPeriod,Enums.Region departureRegion,Enums.Region destinationRegion,Company companyForFlightAdmin,Company companyForTravelAdmin ){
        BigDecimal averageMarginFromBookingsForDepartureFlight =  bookingRepository.getAverageMarginFromBookingsForDepartureFlight(flightPeriod, departureRegion, destinationRegion, companyForFlightAdmin, companyForTravelAdmin);
        BigDecimal averageMarginFromBookingsForReturnFlight   = bookingRepository.getAverageMarginFromBookingsForReturnFlight(flightPeriod, departureRegion, destinationRegion, companyForFlightAdmin, companyForTravelAdmin);
        return  averageMarginFromBookingsForDepartureFlight.add(averageMarginFromBookingsForReturnFlight).divide(new BigDecimal(2)) ;
    }

}
