package com.realdolmen.controller.convertor;

import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.location.Location;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@FacesConverter(value = "locationConverter")
public class LocationConverter implements Converter {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.length() == 0) {
            return null;
        }else {
            Integer id = Integer.parseInt(value);
            Location location = entityManager.find(Location.class,id);
            return location;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object location) {
        return (location == null || location.toString().isEmpty()) ? "" : String.valueOf(((Location) location).getId());
    }
}
