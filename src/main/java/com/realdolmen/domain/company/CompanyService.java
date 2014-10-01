package com.realdolmen.domain.company;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CompanyService {
    @Inject
    private CompanyRepository companyRepository;
}
