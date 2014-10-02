package com.realdolmen.domain.company;

import com.realdolmen.domain.AbstractRepositoy;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class CompanyRepository extends AbstractRepositoy<Company> {
}
