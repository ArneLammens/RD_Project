package com.realdolmen.domain.country;

import com.realdolmen.session.CountrySession;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CountryService {

    @Inject
    private CountryRepository countryRepository;

    public void persistAllowedAndDisallowedCountries(List<Country> allowedCountries,List<Country> disallowedCountries){
      countryRepository.update(allowedCountries,disallowedCountries);
    }



}
