package com.realdolmen.controller.datamodel;

import com.realdolmen.domain.flight.Flight;
import org.primefaces.model.SortOrder;

import java.util.Comparator;

/**
 * Created by BPTAT47 on 12/10/2014.
 */
    public class LazyFlightSorter implements Comparator<Flight> {

        private String sortField;

        private SortOrder sortOrder;

        public LazyFlightSorter(String sortField, SortOrder sortOrder) {
            this.sortField = sortField;
            this.sortOrder = sortOrder;
        }

        public int compare(Flight flight, Flight flight2) {
            try {
                Object value1 = Flight.class.getField(this.sortField).get(flight);
                Object value2 = Flight.class.getField(this.sortField).get(flight2);

                int value = ((Comparable)value1).compareTo(value2);

                return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
            }
            catch(Exception e) {
                throw new RuntimeException();
            }
        }
    }

