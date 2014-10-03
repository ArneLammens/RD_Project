package com.realdolmen.session;

import com.realdolmen.domain.login.Login;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by ALMAU78 on 1/10/2014.
 */
@Named
@SessionScoped
public class LoginSession implements Serializable {

    private Login login;


    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
