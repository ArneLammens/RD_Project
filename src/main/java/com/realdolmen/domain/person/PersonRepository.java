package com.realdolmen.domain.person;

import com.realdolmen.domain.AbstractRepositoy;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class PersonRepository extends AbstractRepositoy<Person> {


}
