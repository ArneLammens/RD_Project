package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.login.Login;
import com.realdolmen.domain.login.LoginAttempt;
import com.realdolmen.domain.login.LoginService;
import com.realdolmen.session.LoginSession;
import com.realdolmen.util.Message;
import com.realdolmen.util.RedirectEnum;
import org.slf4j.Logger;

import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by BPTAT47 on 6/10/2014.
 */
@Named
@ViewScoped
public class LoginController implements Serializable {

    public static final String RESOURCE_BUNDLE_VALIDATION = "resourceBundle/ValidationMessages";
    private Login login;

    @Inject
    private LoginService loginService;

    @Inject
    private Logger logger;

    @Inject
    Event<FacesMessage> event;

    @Inject
    private LoginSession loginSession;

    public void init() {
        login = new Login();
        logger.info("Login info");
    }

    public String loginTo() {

            if(stringIsEmpty(login.getEmail()) && stringIsEmpty(login.getPassword())){
                event.fire(new Message().warning("Your email and password is empty"));
            }
            Login foundLogin =  loginService.attempt(login);
            if (foundLogin != null) {
                loginSession.setLogin(foundLogin);
                return RedirectEnum.REDIRECT.INDEX.getUrl();
            }else{
                return "";
            }


    }

    public boolean stringIsEmpty(String  toBeChecked){
       if(toBeChecked.trim().length() == 0){
           return true;
       }else {
          return false;
       }
    }


    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        return RedirectEnum.REDIRECT.INDEX.getUrl();
    }


    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

}
