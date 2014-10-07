package com.realdolmen.domain;

/**
 * Created by BPTAT47 on 1/10/2014.
 */
public class Enums {

    public enum Region {
        AFRICA("region.Africa"),
        EUROPE("region.Europe"),
        ASIA("region.Asia"),
        NORTH_AMERICA("region.North_America"),
        SOUTH_AMERICA("region.South_America"),
        ANTARCTICA("region.Antarctica"),
        AUSTRALIA("region.Australia");

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

    public enum DayOfTheWeek{
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday"),
        SUNDAY("Sunday");

        private final String label;

        DayOfTheWeek(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

    }
    public enum RolesForACompany{
        FLIGHT_ADMIN,
        TRAVEL_ADMIN;

    }


}
