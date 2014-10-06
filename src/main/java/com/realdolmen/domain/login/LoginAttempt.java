package com.realdolmen.domain.login;

/**
 * Created by BPTAT47 on 6/10/2014.
 */
public class LoginAttempt {

    private boolean succes;

    public LoginAttempt(boolean success) {
        this.succes = success;
    }

    public boolean wasSuccessful() {
        return succes;
    }
}
