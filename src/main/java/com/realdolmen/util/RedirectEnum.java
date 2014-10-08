package com.realdolmen.util;

/**
 * Created by BPTAT47 on 6/10/2014.
 */

public class RedirectEnum {

    public enum REDIRECT {
     INDEX("index?faces-redirect=true"),
     LOGIN("index?faces-redirect=true"),
     TRIPS("index?faces-redirect=true"),
     CREATE_ACCOUNT("createAccount?faces-redirect=true");



        private final String url;

        REDIRECT(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
