package com.realdolmen.domain.location;

import com.realdolmen.domain.AbstractRepositoy;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class LocationRepository extends AbstractRepositoy<Location> {
}
