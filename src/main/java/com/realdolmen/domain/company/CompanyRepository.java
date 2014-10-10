package com.realdolmen.domain.company;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;


@Stateless
public class CompanyRepository extends AbstractRepositoy<Company> {

    public List<Company> getAllCompanies(){
       return entityManager.createNamedQuery("Company.getAllCompanies",Company.class)
               .setParameter("rolesForACompany", Enums.RolesForACompany.FLIGHT_ADMIN).getResultList();
    }
}
