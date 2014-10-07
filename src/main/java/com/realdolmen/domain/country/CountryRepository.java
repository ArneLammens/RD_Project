package com.realdolmen.domain.country;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CountryRepository extends AbstractRepositoy<Country>
{
    @Inject
    private Logger logger;
    public List<Country> getAllApprovedCountriesOfARegion(Enums.Region region)
    {
        logger.info("Get all approved countries for the region "+region);
        return  entityManager.createNamedQuery("Country.getAllCountriesOfARegionApprovedOrDisapproved",Country.class)
                .setParameter("region",region)
                .setParameter("approved",true).getResultList();

    }
    public List<Country> getAllDisapprovedCountriesOfARegion(Enums.Region region)
    {
        logger.info("Get all disapproved countries for the region "+region);
        return  entityManager.createNamedQuery("Country.getAllCountriesOfARegionApprovedOrDisapproved",Country.class)
                .setParameter("region",region)
                .setParameter("approved",false).getResultList();

    }


}
