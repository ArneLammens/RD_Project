package com.realdolmen.domain.login;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by BPTAT47 on 6/10/2014.
 */
@Stateless
public class LoginService {

    @Inject
    private LoginRepository loginRepository;

   public LoginAttempt attempt ( Login login){
      Login foundLogin =  loginRepository.retrievePersonWithGivenNameAndPassword(login);
       return new LoginAttempt(isValidLogin(foundLogin,login)) ;
   }

    private boolean isValidLogin(Login foundLogin, Login login) {
        return foundLogin!=null && login.getPassword().equals(foundLogin.getPassword());
    }



}
