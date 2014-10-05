package com.realdolmen.domain.country;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CountryService {
    @Inject
private CountryRepository countryRepository;
}
