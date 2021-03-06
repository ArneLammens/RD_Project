package login;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.company.Company;
import com.realdolmen.domain.location.LocationRepository;
import com.realdolmen.domain.login.Login;
import com.realdolmen.session.LoginSession;
import common.AbstractArquillianTestCase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class LoginSessionTest extends AbstractArquillianTestCase {

    @Inject
    private LoginSession loginSession;

    @Test
    public void sessionLoginTester() throws Exception {
        Login insertedLogin = new Login(null,"admin","admin", "admin", Enums.Roles.ADMIN, new Company("test", Enums.RolesForACompany.FLIGHT_ADMIN));
        loginSession.setLogin(insertedLogin);
        Login retrievedLoginFromSession = loginSession.getLogin();
        Assert.assertEquals(insertedLogin, retrievedLoginFromSession);
    }
}
