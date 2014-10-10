package com.realdolmen.controller.convertor;

import com.realdolmen.domain.company.Company;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by BPTAT47 on 9/10/2014.
 */
@Named
@FacesConverter(value = "companyConverter")
public class CompanyConverter  implements Converter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.length() == 0) {
            return null;
        }else {
            Integer id = Integer.parseInt(value);
            return  entityManager.find(Company.class,id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object company) {
        return (company == null || company.toString().isEmpty()) ? "" : String.valueOf(((Company) company).getId());
    }
}