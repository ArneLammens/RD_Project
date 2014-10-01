package com.realdolmen.domain;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
public class Enums {

    public enum Region {
        AFRICA("Africa"),
        EUROPE("Europe"),
        ASIA("ASIA"),
        NORTH_AMERICA("North America"),
        SOUTH_AMERICA("South America"),
        ANTARCTICA("Antarctica"),
        AUSTRALIA("Australia");

        private final String label;

        Region(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Roles {
        ADMIN("Admin"),
        USER("User"),
        FLIGHT_ADMIN("Flight Admin"),
        TRAVEL_AGENT("Travel Agent");


        private final String label;

        Roles(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }


}
