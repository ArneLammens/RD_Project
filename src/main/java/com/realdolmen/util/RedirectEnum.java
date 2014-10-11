package com.realdolmen.util;

/**
 * Created by BPTAT47 on 6/10/2014.
 */

public class RedirectEnum {

    public enum REDIRECT {
     INDEX("index?faces-redirect=true"),
     LOGIN("login?faces-redirect=true"),
     TRIPS("trips?faces-redirect=true"),
     BOOKING("booking?faces-redirect=true"),
     CREATE_ACCOUNT("createAccount?faces-redirect=true"),

     THANK_YOU("thankyou?faces-redirect=true"),
     CREATE_FLIGHT("createFlight?faces-redirect=true"),
     REMOVE_FLIGHT("removeFlight?faces-redirect=true"),
     REMOVE_AIRLINE("removeAirline?faces-redirect=true"),

     ADMIN_COUNTRY("countryManagement?faces-redirect=true"),
     ADMIN_MARGIN("marginManagement?faces-redirect=true"),
     REPORT("report?faces-redirect=true");





        private final String url;

        REDIRECT(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }


}
