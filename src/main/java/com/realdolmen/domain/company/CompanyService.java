package com.realdolmen.domain.company;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CompanyService {
    @Inject
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies()
    {
        return companyRepository.findAll();
    }
}
