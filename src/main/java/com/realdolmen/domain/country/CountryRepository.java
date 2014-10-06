package com.realdolmen.domain.country;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CountryRepository extends AbstractRepositoy<Country>
{
    public List<Country> getAllApprovedCountriesOfARegion(Enums.Region region)
    {
        return  entityManager.createNamedQuery("Country.getAllCountriesOfARegionApprovedOrDisapproved",Country.class)
                .setParameter("region",region)
                .setParameter("approved",true).getResultList();

    }
    public List<Country> getAllDisapprovedCountriesOfARegion(Enums.Region region)
    {
        return  entityManager.createNamedQuery("Country.getAllCountriesOfARegionApprovedOrDisapproved",Country.class)
                .setParameter("region",region)
                .setParameter("approved",false).getResultList();

    }

}
