package com.realdolmen.domain.login;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;
import com.realdolmen.domain.person.Person;
import com.realdolmen.util.Message;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
@Stateless
public class LoginRepository extends AbstractRepositoy<Login> {

    public Login retrievePersonWithGivenNameAndPassword(Login login)throws Exception {
        try {
            Person person = entityManager.createNamedQuery("Person.retrievePersonWithGivenEmailAndPassword", Person.class)
                    .setParameter("email", login.getEmail())
                    .setParameter("password", login.getPassword()).getSingleResult();
            return new Login(person.getId(), person.getEmail(), person.getPassword(), person.getRole());
        } catch (Exception e) {
            throw new NoResultException();
        }

    }
}

