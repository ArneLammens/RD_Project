package com.realdolmen.domain.company;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;


@Stateless
public class CompanyRepository extends AbstractRepositoy<Company> {

    public List<Company> getAllCompanies(Enums.RolesForACompany role){
       return entityManager.createNamedQuery("Company.getAllCompanies",Company.class)
               .setParameter("rolesForACompany",role).getResultList();
    }

    public void removeCompany(Company company)
    {
        entityManager.remove(entityManager.merge(company));
    }
}
