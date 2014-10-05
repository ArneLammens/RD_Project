package com.realdolmen.session;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.Country;
import com.realdolmen.domain.country.CountryRepository;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Startup
@ApplicationScoped
public class CountrySession {
    @Inject
    private CountryRepository countryRepository;

    @Inject
    private Logger logger;

    private List<Country> AFRICA_Approved;
    private List<Country> AFRICA_Disapproved;
    private List<Country> EUROPE_Approved;
    private List<Country> EUROPE_Disapproved;
    private List<Country> ASIA_Approved;
    private List<Country> ASIA_Disapproved;
    private List<Country> NORTH_AMERICA_Approved;
    private List<Country> NORTH_AMERICA_Disapproved;
    private List<Country> SOUTH_AMERICA_Approved;
    private List<Country> SOUTH_AMERICA_Disapproved;
    private List<Country> ANTARCTICA_Approved;
    private List<Country> ANTARCTICA_Disapproved;
    private List<Country> AUSTRALIA_Approved;
    private List<Country> AUSTRALIA_Disapproved;

    @PostConstruct
    private void getAllCountries() {
        for (Enums.Region region : Enums.Region.values()) {
            if(region.equals(Enums.Region.AFRICA))
            {
                logger.info("get all approved ");
                AFRICA_Approved=countryRepository.getAllApprovedCountriesOfARegion(region);
                AFRICA_Disapproved=countryRepository.getAllDisapprovedCountriesOfARegion(region);
            }
            else if(region.equals(Enums.Region.EUROPE))
            {
                EUROPE_Approved=countryRepository.getAllApprovedCountriesOfARegion(region);
                EUROPE_Disapproved=countryRepository.getAllDisapprovedCountriesOfARegion(region);
            }
            else if(region.equals(Enums.Region.ASIA))
            {
                ASIA_Approved=countryRepository.getAllApprovedCountriesOfARegion(region);
                ASIA_Disapproved=countryRepository.getAllDisapprovedCountriesOfARegion(region);
            }
            else if(region.equals(Enums.Region.NORTH_AMERICA))
            {
                NORTH_AMERICA_Approved=countryRepository.getAllApprovedCountriesOfARegion(region);
                NORTH_AMERICA_Disapproved=countryRepository.getAllDisapprovedCountriesOfARegion(region);
            }
            else if(region.equals(Enums.Region.SOUTH_AMERICA))
            {
                SOUTH_AMERICA_Approved=countryRepository.getAllApprovedCountriesOfARegion(region);
                SOUTH_AMERICA_Disapproved=countryRepository.getAllDisapprovedCountriesOfARegion(region);
            }
            else if(region.equals(Enums.Region.ANTARCTICA))
            {
                ANTARCTICA_Approved=countryRepository.getAllApprovedCountriesOfARegion(region);
                ANTARCTICA_Disapproved=countryRepository.getAllDisapprovedCountriesOfARegion(region);
            }
            else if(region.equals(Enums.Region.AUSTRALIA))
            {
                AUSTRALIA_Approved=countryRepository.getAllApprovedCountriesOfARegion(region);
                AUSTRALIA_Disapproved=countryRepository.getAllDisapprovedCountriesOfARegion(region);
            }
        }
    }

    public List<Country> getAFRICA_Approved() {
        return AFRICA_Approved;
    }

    public void setAFRICA_Approved(List<Country> AFRICA_Approved) {
        this.AFRICA_Approved = AFRICA_Approved;
    }

    public List<Country> getAFRICA_Disapproved() {
        return AFRICA_Disapproved;
    }

    public void setAFRICA_Disapproved(List<Country> AFRICA_Disapproved) {
        this.AFRICA_Disapproved = AFRICA_Disapproved;
    }

    public List<Country> getEUROPE_Approved() {
        return EUROPE_Approved;
    }

    public void setEUROPE_Approved(List<Country> EUROPE_Approved) {
        this.EUROPE_Approved = EUROPE_Approved;
    }

    public List<Country> getEUROPE_Disapproved() {
        return EUROPE_Disapproved;
    }

    public void setEUROPE_Disapproved(List<Country> EUROPE_Disapproved) {
        this.EUROPE_Disapproved = EUROPE_Disapproved;
    }

    public List<Country> getASIA_Approved() {
        return ASIA_Approved;
    }

    public void setASIA_Approved(List<Country> ASIA_Approved) {
        this.ASIA_Approved = ASIA_Approved;
    }

    public List<Country> getASIA_Disapproved() {
        return ASIA_Disapproved;
    }

    public void setASIA_Disapproved(List<Country> ASIA_Disapproved) {
        this.ASIA_Disapproved = ASIA_Disapproved;
    }

    public List<Country> getNORTH_AMERICA_Approved() {
        return NORTH_AMERICA_Approved;
    }

    public void setNORTH_AMERICA_Approved(List<Country> NORTH_AMERICA_Approved) {
        this.NORTH_AMERICA_Approved = NORTH_AMERICA_Approved;
    }

    public List<Country> getNORTH_AMERICA_Disapproved() {
        return NORTH_AMERICA_Disapproved;
    }

    public void setNORTH_AMERICA_Disapproved(List<Country> NORTH_AMERICA_Disapproved) {
        this.NORTH_AMERICA_Disapproved = NORTH_AMERICA_Disapproved;
    }

    public List<Country> getSOUTH_AMERICA_Approved() {
        return SOUTH_AMERICA_Approved;
    }

    public void setSOUTH_AMERICA_Approved(List<Country> SOUTH_AMERICA_Approved) {
        this.SOUTH_AMERICA_Approved = SOUTH_AMERICA_Approved;
    }

    public List<Country> getSOUTH_AMERICA_Disapproved() {
        return SOUTH_AMERICA_Disapproved;
    }

    public void setSOUTH_AMERICA_Disapproved(List<Country> SOUTH_AMERICA_Disapproved) {
        this.SOUTH_AMERICA_Disapproved = SOUTH_AMERICA_Disapproved;
    }

    public List<Country> getANTARCTICA_Approved() {
        return ANTARCTICA_Approved;
    }

    public void setANTARCTICA_Approved(List<Country> ANTARCTICA_Approved) {
        this.ANTARCTICA_Approved = ANTARCTICA_Approved;
    }

    public List<Country> getANTARCTICA_Disapproved() {
        return ANTARCTICA_Disapproved;
    }

    public void setANTARCTICA_Disapproved(List<Country> ANTARCTICA_Disapproved) {
        this.ANTARCTICA_Disapproved = ANTARCTICA_Disapproved;
    }

    public List<Country> getAUSTRALIA_Approved() {
        return AUSTRALIA_Approved;
    }

    public void setAUSTRALIA_Approved(List<Country> AUSTRALIA_Approved) {
        this.AUSTRALIA_Approved = AUSTRALIA_Approved;
    }

    public List<Country> getAUSTRALIA_Disapproved() {
        return AUSTRALIA_Disapproved;
    }

    public void setAUSTRALIA_Disapproved(List<Country> AUSTRALIA_Disapproved) {
        this.AUSTRALIA_Disapproved = AUSTRALIA_Disapproved;
    }
}
