//package com.realdolmen.util;
//
//import com.realdolmen.domain.Enums;
//import com.realdolmen.domain.company.Company;
//import com.realdolmen.domain.country.Country;
//import com.realdolmen.domain.country.CountryRepository;
//
//import com.realdolmen.domain.country.CountryService;
//import com.realdolmen.domain.flight.Flight;
//import com.realdolmen.domain.flight.FlightPeriod;
//import com.realdolmen.domain.location.Location;
//
//import com.realdolmen.domain.person.Person;
//import com.realdolmen.domain.person.PersonBuilder;
//import com.realdolmen.domain.trip.Trip;
//import org.slf4j.Logger;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//
//import javax.ejb.Stateless;
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//
//@Startup
//@Singleton
//public class TestData {
///*
//    @PersistenceContext(unitName = "users")
//    private EntityManager entityManager;
//    @Inject
//    private Logger logger;
//
//    private EncryptUtil encryptUtil = new EncryptUtil();
//
//    @PostConstruct
//    public void init(){
//        addCompanies();
//        addApprovedAndDisapprovedCountriesWithDifferentRegions();
//        addUsersWithDifferentRoles();
//        addLocation();
//        addFlights();
//        addTrips();
//    }
//
//    public void addCompanies()
//    {
//        logger.info("/////************************************INJECTING COMPANIES*************************************/////");
//
//        entityManager.persist(new Company("JetAir",Enums.RolesForACompany.FLIGHT_ADMIN));
//        entityManager.persist(new Company("RyanAIr",Enums.RolesForACompany.FLIGHT_ADMIN));
//        entityManager.persist(new Company("Neckermann",Enums.RolesForACompany.TRAVEL_ADMIN));
//
//    }
//
//    public void addApprovedAndDisapprovedCountriesWithDifferentRegions()
//    {
//        logger.info("/////************************************INJECTING COUNRTIES*************************************/////");
//        entityManager.persist(new Country("Belgium",true,Enums.Region.EUROPE));
//        entityManager.persist(new Country("Germany",true,Enums.Region.EUROPE));
//        entityManager.persist(new Country("Spain",true,Enums.Region.EUROPE));
//        entityManager.persist(new Country("France",true,Enums.Region.EUROPE));
//        entityManager.persist(new Country("Italy",false,Enums.Region.EUROPE));
//        entityManager.persist(new Country("Netherland",false,Enums.Region.EUROPE));
//        entityManager.persist(new Country("Algeria",true,Enums.Region.AFRICA));
//        entityManager.persist(new Country("Angola",true,Enums.Region.AFRICA));
//        entityManager.persist(new Country("Benin",true,Enums.Region.AFRICA));
//        entityManager.persist(new Country("Botswana",false,Enums.Region.AFRICA));
//        entityManager.persist(new Country("Burkina Faso",false,Enums.Region.AFRICA));
//        entityManager.persist(new Country("Burundi",false,Enums.Region.AFRICA));
//        entityManager.persist(new Country("Afghanistan",false,Enums.Region.ASIA));
//        entityManager.persist(new Country("Bahrain",true,Enums.Region.ASIA));
//        entityManager.persist(new Country("Bangladesh",true,Enums.Region.ASIA));
//        entityManager.persist(new Country("Bhutan",true,Enums.Region.ASIA));
//        entityManager.persist(new Country("Brunei",true,Enums.Region.ASIA));
//        entityManager.persist(new Country("Burma",false,Enums.Region.ASIA));
//        entityManager.persist(new Country("Australia",true,Enums.Region.AUSTRALIA));
//        entityManager.persist(new Country("Fiji",true,Enums.Region.AUSTRALIA));
//        entityManager.persist(new Country("Kiribati",false,Enums.Region.AUSTRALIA));
//        entityManager.persist(new Country("Micronesia",false,Enums.Region.AUSTRALIA));
//        entityManager.persist(new Country("United States",true,Enums.Region.NORTH_AMERICA));
//        entityManager.persist(new Country("Mexico",false,Enums.Region.NORTH_AMERICA));
//        entityManager.persist(new Country("Canada",true,Enums.Region.NORTH_AMERICA));
//        entityManager.persist(new Country("Cuba",false,Enums.Region.NORTH_AMERICA));
//        entityManager.persist(new Country("Dominican Republic",true,Enums.Region.NORTH_AMERICA));
//        entityManager.persist(new Country("Brazil",true,Enums.Region.SOUTH_AMERICA));
//        entityManager.persist(new Country("Colombia",false,Enums.Region.SOUTH_AMERICA));
//        entityManager.persist(new Country("Peru",true,Enums.Region.SOUTH_AMERICA));
//        entityManager.persist(new Country("Venezuela",true,Enums.Region.SOUTH_AMERICA));
//        entityManager.persist(new Country("Chile",true,Enums.Region.SOUTH_AMERICA));
//        entityManager.persist(new Country("Ecuador",false,Enums.Region.SOUTH_AMERICA));
//    }
//
//
//    public void addUsersWithDifferentRoles() {
//       logger.info("/////************************************INJECTING USERS*************************************/////");
//
//        List<Company> flightCompany=entityManager.createQuery("SELECT c FROM Company c where c.name ='JetAir'").getResultList();
//        List<Company> travelCompany= entityManager.createQuery("SELECT c FROM Company c where c.name ='Neckermann'").getResultList();
//        List<Country> countries=entityManager.createQuery("SELECT c FROM Country c WHERE c.name='Belgium'").getResultList();
//       entityManager.persist(new Person("admin@hotmail.com",new EncryptUtil().encryptPassword("adminProfile"),"126B","9500","Geraardsbergen",countries.get(0), Enums.Region.EUROPE,"administrator","administrator",new Date(),Enums.Roles.ADMIN));
//       entityManager.persist(new Person("flightadmin@hotmail.com",new EncryptUtil().encryptPassword("flightadmin"),"126B","9500","Arnhem",countries.get(0), Enums.Region.EUROPE,"flightadmin","flightadmin",new Date(),Enums.Roles.FLIGHT_ADMIN,flightCompany.get(0)));
//       entityManager.persist(new Person("travelagent@hotmail.com",new EncryptUtil().encryptPassword("travelagent"),"126B","9500","Lille",countries.get(0),Enums.Region.EUROPE,"travelagent","travelagent",new Date(),Enums.Roles.TRAVEL_AGENT,travelCompany.get(0)));
//       entityManager.persist(new Person("user@hotmail.com",new EncryptUtil().encryptPassword("userProfile"),"126B","9500","Haaltert",countries.get(0), Enums.Region.EUROPE,"administrator","administrator",new Date(),Enums.Roles.USER));
//    }
//
//
//    public void addLocation()
//    {
//        logger.info("/////************************************INJECTING COUNRTIES*************************************/////");
//        Random randomGenerator = new Random();
//
//        List<Country> allCountries;
//
//        Query query = entityManager.createQuery("SELECT c FROM Country c WHERE c.approved=true");
//
//        if (query.getResultList().isEmpty()) {
//            allCountries = null;
//        }
//        else
//        {
//            allCountries=query.getResultList();
//        }
//        for (int i = 0; i < allCountries.size(); i++) {
//            int price=randomGenerator.nextInt(500);
//            entityManager.persist(new Location("test"+i,allCountries.get(i),new BigDecimal(price)));
//        }
//
//    }
//
//    public void addFlights()
//    {
//        Random randomGenerator = new Random();
//        List<Location>allLocations= entityManager.createQuery("SELECT l FROM Location l").getResultList();
//        logger.info(Enums.Roles.FLIGHT_ADMIN.toString());
//        List<Person> person =  entityManager.createQuery("SELECT p FROM Person p WHERE p.role =:role").setParameter("role",Enums.Roles.FLIGHT_ADMIN).getResultList();
//
//        for (int i = 0; i <200; i++) {
//
//        int randomInt = randomGenerator.nextInt(allLocations.size());
//            Random randomGenerator2 = new Random();
//        int radomInt2=randomGenerator2.nextInt(allLocations.size());
//            int randomInt3=randomGenerator2.nextInt(7);
//        logger.info("/////************************************INJECTING FLIGHT*************************************/////");
//        entityManager.persist(new Flight("abc"+i,allLocations.get(randomInt),allLocations.get(radomInt2),45,45,13.58,5,new BigDecimal((randomInt*10)),randomInt3+1,person.get(0),new Date(),new Date(),new FlightPeriod(new Date(1412632800000l), new Date(1444168800000l)),Enums.DayOfTheWeek.valueOf(randomInt3+1)));
//            entityManager.persist(new Flight("abcr"+i,allLocations.get(radomInt2),allLocations.get(randomInt),45,45,13.58,5,new BigDecimal((randomInt*10)),randomInt3+1,person.get(0),new Date(),new Date(),new FlightPeriod(new Date(1412632800000l), new Date(1444168800000l)),Enums.DayOfTheWeek.valueOf(randomInt3+1)));
//        }
//
//    }
//    public void addTrips()
//    {
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        int dayOfWeekDeparture= c.get(Calendar.DAY_OF_WEEK);
//
//        Calendar c2 = Calendar.getInstance();
//        c2.setTime(new Date(new Date().getTime()+604800000l));
//        int dayOfWeekDestination= c.get(Calendar.DAY_OF_WEEK);
//
//        List<Country> country=entityManager.createQuery("SELECT c FROM Country c where c.name ='Belgium'").getResultList();
//        List<Flight>departureFlight=entityManager.createQuery("SELECT f FROM Flight f WHERE f.departure.country=:country AND f.availableSeats >=30 AND :departuredate between f.period.startDate AND f.period.endDate AND f.dayOfTheWeek = :dayOfTheWeek").setParameter("country", country.get(0)).setParameter("departuredate",new Date()).setParameter("dayOfTheWeek",Enums.DayOfTheWeek.valueOf(dayOfWeekDeparture)).getResultList();
//        List<Flight>returnFlight=entityManager.createQuery("SELECT f FROM Flight f WHERE f.destination.country=:country AND f.availableSeats >=30 AND :returnDate between f.period.startDate AND f.period.endDate AND f.dayOfTheWeek = :dayOfTheWeek").setParameter("country", country.get(0)).setParameter("returnDate",new Date()).setParameter("dayOfTheWeek",Enums.DayOfTheWeek.valueOf(dayOfWeekDestination)).getResultList();
//        List<Person>travelAgent= entityManager.createQuery("SELECT p FROM Person p WHERE p.role =:role").setParameter("role", Enums.Roles.TRAVEL_AGENT).getResultList();
//
//
//
//        Trip trip = new Trip("testTrip",departureFlight.get(0),returnFlight.get(0),travelAgent.get(0),30,30,new Date(),new Date(new Date().getTime()+604800000l));
//
//        entityManager.persist(trip);
//
//        if(trip.getId()!=null) {
//            Flight departflight = departureFlight.get(0);
//            departflight.setAvailableSeats(departflight.getAvailableSeats() - 30);
//            Flight retFlight = returnFlight.get(0);
//            departflight.setAvailableSeats(retFlight.getAvailableSeats() - 30);
//            entityManager.merge(departflight);
//            entityManager.merge(retFlight);
//        }
//
//    }
//
//
//
//
//}
