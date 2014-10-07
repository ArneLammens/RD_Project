package com.realdolmen.util;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.country.CountryRepository;

import com.realdolmen.domain.country.CountryService;
import com.realdolmen.domain.flight.Flight;
import com.realdolmen.domain.location.Location;

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
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.realdolmen.domain.person.PersonBuilder.aPerson;


@Startup
@Singleton
public class TestData {

    @PersistenceContext(unitName = "users")
    private EntityManager entityManager;
    @Inject
    private Logger logger;

    @PostConstruct
    public void init(){
        addCompanies();
        addUsersWithDifferentRoles();
        addApprovedAndDisapprovedCountriesWithDifferentRegions();
        addLocation();
    }

    public void addCompanies()
    {
        logger.info("/////************************************INJECTING COMPANIES*************************************/////");
        entityManager.persist(new Company("RyanAir",Enums.RolesForACompany.FLIGHT_ADMIN));
        entityManager.persist(new Company("JetAir",Enums.RolesForACompany.FLIGHT_ADMIN));
        entityManager.persist(new Company("Neckermann",Enums.RolesForACompany.TRAVEL_ADMIN));
        entityManager.persist(new Company("Mare Tours",Enums.RolesForACompany.TRAVEL_ADMIN));
    }


    public void addUsersWithDifferentRoles() {
       logger.info("/////************************************INJECTING USERS*************************************/////");

        Company flightCompany=(Company) entityManager.createQuery("SELECT c FROM Company c where c.name =JetAir ").getSingleResult();
        Company travelCompany=(Company) entityManager.createQuery("SELECT c FROM Company c where c.name =Neckermann ").getSingleResult();
       entityManager.persist(new Person("admin@hotmail.com","adminProfile","126B","9500","Geraardsbergen", Enums.Region.EUROPE,"administrator","administrator",new Date(),Enums.Roles.ADMIN));
       entityManager.persist(new Person("flightadmin@hotmail.com","flightadmin","126B","9500","Arnhem", Enums.Region.EUROPE,"flightadmin","flightadmin",new Date(),Enums.Roles.FLIGHT_ADMIN,flightCompany));
       entityManager.persist(new Person("travelagent@hotmail.com","travelagent","126B","9500","Lille",Enums.Region.EUROPE,"travelagent","travelagent",new Date(),Enums.Roles.TRAVEL_AGENT,travelCompany));
       entityManager.persist(new Person("user@hotmail.com","userProfile","126B","9500","Haaltert", Enums.Region.EUROPE,"administrator","administrator",new Date(),Enums.Roles.USER));
    }
    public void addApprovedAndDisapprovedCountriesWithDifferentRegions()
    {
        logger.info("/////************************************INJECTING COUNRTIES*************************************/////");
        entityManager.persist(new Country("Belgium",true,Enums.Region.EUROPE));
        entityManager.persist(new Country("Germany",true,Enums.Region.EUROPE));
        entityManager.persist(new Country("Spain",true,Enums.Region.EUROPE));
        entityManager.persist(new Country("France",true,Enums.Region.EUROPE));
        entityManager.persist(new Country("Italy",false,Enums.Region.EUROPE));
        entityManager.persist(new Country("Netherland",false,Enums.Region.EUROPE));
        entityManager.persist(new Country("Algeria",true,Enums.Region.AFRICA));
        entityManager.persist(new Country("Angola",true,Enums.Region.AFRICA));
        entityManager.persist(new Country("Benin",true,Enums.Region.AFRICA));
        entityManager.persist(new Country("Botswana",false,Enums.Region.AFRICA));
        entityManager.persist(new Country("Burkina Faso",false,Enums.Region.AFRICA));
        entityManager.persist(new Country("Burundi",false,Enums.Region.AFRICA));
        entityManager.persist(new Country("Afghanistan",false,Enums.Region.ASIA));
        entityManager.persist(new Country("Bahrain",true,Enums.Region.ASIA));
        entityManager.persist(new Country("Bangladesh",true,Enums.Region.ASIA));
        entityManager.persist(new Country("Bhutan",true,Enums.Region.ASIA));
        entityManager.persist(new Country("Brunei",true,Enums.Region.ASIA));
        entityManager.persist(new Country("Burma",false,Enums.Region.ASIA));
        entityManager.persist(new Country("Australia",true,Enums.Region.AUSTRALIA));
        entityManager.persist(new Country("Fiji",true,Enums.Region.AUSTRALIA));
        entityManager.persist(new Country("Kiribati",false,Enums.Region.AUSTRALIA));
        entityManager.persist(new Country("Micronesia",false,Enums.Region.AUSTRALIA));
        entityManager.persist(new Country("United States",true,Enums.Region.NORTH_AMERICA));
        entityManager.persist(new Country("Mexico",false,Enums.Region.NORTH_AMERICA));
        entityManager.persist(new Country("Canada",true,Enums.Region.NORTH_AMERICA));
        entityManager.persist(new Country("Cuba",false,Enums.Region.NORTH_AMERICA));
        entityManager.persist(new Country("Dominican Republic",true,Enums.Region.NORTH_AMERICA));
        entityManager.persist(new Country("Brazil",true,Enums.Region.SOUTH_AMERICA));
        entityManager.persist(new Country("Colombia",false,Enums.Region.SOUTH_AMERICA));
        entityManager.persist(new Country("Peru",true,Enums.Region.SOUTH_AMERICA));
        entityManager.persist(new Country("Venezuela",true,Enums.Region.SOUTH_AMERICA));
        entityManager.persist(new Country("Chile",true,Enums.Region.SOUTH_AMERICA));
        entityManager.persist(new Country("Ecuador",false,Enums.Region.SOUTH_AMERICA));
    }

    public void addLocation()
    {
        List<Country> allCountries;
        Query query = entityManager.createQuery("SELECT c FROM Country c ");
        if (query.getResultList() == null) {
            allCountries = null;
        }
        else
        {
            allCountries=query.getResultList();
        }
        for (int i = 0; i < allCountries.size(); i++) {
            entityManager.persist(new Location("test"+i,allCountries.get(i),new BigDecimal(0)));
        }

    }

    public void addFlights()
    {

    }




}
