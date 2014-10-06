package country;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.country.CountryRepository;
import common.AbstractArquillianTestCase;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CountryRepositoryTest extends AbstractArquillianTestCase {

    @Inject
    private CountryRepository countryRepository;
    @Inject
    private Logger logger;

    @Test
    public void getApprovedCountriesOfARegion() throws Exception {
        logger.info("Check approved countries");
        assertEquals(countryRepository.getAllApprovedCountriesOfARegion(Enums.Region.EUROPE).size(), 3);
    }

    @Test
    public void getDisapprovedCountriesOfARegion() throws Exception {
        logger.info("Check disapproved countries");
        assertEquals(countryRepository.getAllDisapprovedCountriesOfARegion(Enums.Region.EUROPE).size(), 2);
    }
}
