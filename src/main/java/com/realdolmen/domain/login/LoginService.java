package com.realdolmen.domain.login;

import com.realdolmen.util.EncryptUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * Created by BPTAT47 on 6/10/2014.
 */
@Stateless
public class LoginService {

    @Inject
    private LoginRepository loginRepository;

    private EncryptUtil encryptUtil = new EncryptUtil();


    public Login attempt(Login login){

        Login foundLogin = loginRepository.retrievePersonWithGivenNameAndPassword(login);
        login.setPassword(encryptUtil.encryptPassword(login.getPassword()));
        LoginAttempt loginAttempt = new LoginAttempt(isValidLogin(foundLogin, login));

        if(loginAttempt.wasSuccessful()){
            return foundLogin;

        }else{
            return null;
        }

    }

    private boolean isValidLogin(Login foundLogin, Login login) {
        return foundLogin != null && login.getPassword().equals(foundLogin.getPassword());
    }


}
