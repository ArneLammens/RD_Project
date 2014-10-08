package com.realdolmen.domain.person;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by ALMAU78 on 1/10/2014.
 */
@Stateless
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    public void createAPerson(Person person){
        personRepository.persist(person);
    }


}
