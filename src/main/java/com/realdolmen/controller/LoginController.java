package com.realdolmen.controller;

import com.realdolmen.domain.login.Login;
import com.realdolmen.domain.login.LoginAttempt;
import com.realdolmen.domain.login.LoginService;
import com.realdolmen.session.LoginSession;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * Created by BPTAT47 on 6/10/2014.
 */
@Named
@ViewScoped
public class LoginController {


    private Login login;

    @Inject
    private LoginService loginService;

    @Inject
    private LoginSession loginSession;

    public void init() {
        login = new Login();
    }

    public String loginTo() {
        LoginAttempt attempt = loginService.attempt(login);
        if (attempt.wasSuccessful()) {
            loginSession.setLogin(login);
            return "";
        } else {
            return "";
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        return "";
    }


    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
