package com.realdolmen.domain.person;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.login.Login;
import com.realdolmen.util.EncryptUtil;

import javax.ejb.Stateless;
import javax.inject.Named;


@Stateless
public class PersonRepository extends AbstractRepositoy<Person> {

    private EncryptUtil encryptUtil = new EncryptUtil();

    @Override
    public void persist(Person person) {
        person.setPassword(encryptUtil.encryptPassword(person.getPassword()));
        super.persist(person);
    }


}
