package com.realdolmen.domain;

import java.util.Calendar;

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
        MONDAY(Calendar.MONDAY),
        TUESDAY(Calendar.TUESDAY),
        WEDNESDAY(Calendar.WEDNESDAY),
        THURSDAY(Calendar.THURSDAY),
        FRIDAY(Calendar.FRIDAY),
        SATURDAY(Calendar.SATURDAY),
        SUNDAY(Calendar.SUNDAY);

        private final int label;

        DayOfTheWeek(int label) {
            this.label = label;
        }

        public int getLabel() {
            return label;
        }

        public static DayOfTheWeek valueOf(int value) {
            for (DayOfTheWeek day : values()) {
                if (day.getLabel() == value) {
                    return day;
                }
            }
            throw new IllegalArgumentException(""+value);
        }

    }
    public enum RolesForACompany{
        FLIGHT_ADMIN("Flight Admin"),
        TRAVEL_ADMIN("Travel Admin");

        private final String label;

        RolesForACompany(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }


}
