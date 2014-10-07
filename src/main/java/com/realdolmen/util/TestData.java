package com.realdolmen.util;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.country.CountryRepository;
import com.realdolmen.domain.person.Person;
import com.realdolmen.domain.person.PersonBuilder;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;

import static com.realdolmen.domain.person.PersonBuilder.aPerson;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
@Startup
@Singleton
public class TestData {

    @PersistenceContext(unitName = "users")
    private EntityManager entityManager;
    @Inject
    private Logger logger;

    @PostConstruct
    public void addUsersWithDifferentRoles() {
        logger.info("/////************************************INJECTING users*************************************/////");
       entityManager.persist(new Person("admin@hotmail.com","adminProfile","126B","9500","Geraardsbergen", Enums.Region.EUROPE,"administrator","administrator",new Date(),Enums.Roles.ADMIN));
       entityManager.persist(new Person("flightadmin@hotmail.com","flightadmin","126B","9500","Arnhem", Enums.Region.EUROPE,"flightadmin","flightadmin",new Date(),Enums.Roles.FLIGHT_ADMIN));
       entityManager.persist(new Person("travelagent@hotmail.com","travelagent","126B","9500","Lille",Enums.Region.EUROPE,"travelagent","travelagent",new Date(),Enums.Roles.TRAVEL_AGENT));
       entityManager.persist(new Person("user@hotmail.com","userProfile","126B","9500","Haaltert", Enums.Region.EUROPE,"administrator","administrator",new Date(),Enums.Roles.USER));
    }

}
