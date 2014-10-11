package com.realdolmen.domain.search;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.booking.Booking;
import com.realdolmen.domain.booking.BookingRepository;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.flight.FlightPeriod;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
}
