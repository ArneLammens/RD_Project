package com.realdolmen.util;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.person.PersonBuilder;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;

import static com.realdolmen.domain.person.PersonBuilder.aPerson;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
@Singleton
@Startup
public class TestData {
    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init(){
        addUsersWithDifferentRoles();
    }

    public void addUsersWithDifferentRoles(){
       entityManager.persist(new Person("admin@hotmail.com","admin","126B","9500","Geraardsbergen",new Country("Belgium"), Enums.Region.EUROPE,"administrator","administrator",new Date(),Enums.Roles.ADMIN));
       entityManager.persist(new Person("flightadmin@hotmail.com","flightadmin","126B","9500","Arnhem",new Country("Netherlands"), Enums.Region.EUROPE,"flightadmin","flightadmin",new Date(),Enums.Roles.FLIGHT_ADMIN));
       entityManager.persist(new Person("travelagent@hotmail.com","travelagent","126B","9500","Lille",new Country("France"), Enums.Region.EUROPE,"travelagent","travelagent",new Date(),Enums.Roles.TRAVEL_AGENT));
       entityManager.persist(new Person("user@hotmail.com","user","126B","9500","Haaltert",new Country("Belgium"), Enums.Region.EUROPE,"administrator","administrator",new Date(),Enums.Roles.USER));
    }

}
