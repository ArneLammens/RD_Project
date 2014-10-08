package com.realdolmen.domain.login;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;
import com.realdolmen.domain.person.Person;
import com.realdolmen.util.EncryptUtil;
import com.realdolmen.util.Message;
import org.slf4j.Logger;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
@Stateless
public class LoginRepository extends AbstractRepositoy<Login> {

    public static final String RESOURCE_BUNDLE_VALIDATION = "resourceBundle/ValidationMessages";

    @Inject
    private Logger logger;
    @Inject
    private Event<FacesMessage> event;

    private EncryptUtil encryptUtil = new EncryptUtil();

    public Login retrievePersonWithGivenNameAndPassword(Login login) {
        List<Person> persons = entityManager.createNamedQuery("Person.retrievePersonWithGivenEmailAndPassword", Person.class)
                .setParameter("email", login.getEmail())
                .setParameter("password", encryptUtil.encryptPassword(login.getPassword())).getResultList();

        if (persons.size() == 0) {
            logger.error("Person wasn't found");
            event.fire(new Message().warning(RESOURCE_BUNDLE_VALIDATION, "login.emptyEmailAndPassword"));
        } else {
            Person person = persons.get(0);
            return new Login(person.getId(), person.getName(),person.getEmail(), person.getPassword(), person.getRole(),person.getCountry(),person.getRegion());
        }

        return null;
    }

}


