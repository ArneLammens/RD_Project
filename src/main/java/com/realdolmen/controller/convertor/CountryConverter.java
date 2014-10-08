package com.realdolmen.controller.convertor;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.country.CountryRepository;
import com.realdolmen.domain.country.CountryService;

import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by BPTAT47 on 7/10/2014.
 */
@Named
@FacesConverter(value = "countryConverter")
public class CountryConverter  implements Converter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.length() == 0) {
            return null;
        }else {
            Integer id = Integer.parseInt(value);
            Country country = entityManager.find(Country.class,id);
            return country;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object country) {
        return (country == null || country.toString().isEmpty()) ? "" : String.valueOf(((Country) country).getId());
    }
}
