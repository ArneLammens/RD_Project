package com.realdolmen.domain.login;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.person.Person;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
@Stateless
public class LoginRepository extends AbstractRepositoy<Login> {

    public Login retrievePersonWithGivenNameAndPassword(Login login){

        Person person = entityManager.createNamedQuery("Person.retrievePersonWithGivenEmailAndPassword", Person.class)
                .setParameter("email", login.getEmail())
                .setParameter("password", login.getPassword()).getSingleResult();

        if (person == null) {
            return null;
        } else {
            return new Login(person.getId(), person.getEmail(), person.getPassword(), person.getRole());
        }
    }
}
