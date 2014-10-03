package login;

import com.realdolmen.domain.Enums;
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

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class LoginSessionTest {

    @Inject
    private LoginSession loginSession ;

    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage("login")
                .addAsResource("META-INF/testPersistence.xml","META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testAsync() throws Exception {
        Login insertedLogin = new Login("admin", "admin", Enums.Roles.ADMIN);
        loginSession.setLogin(insertedLogin);
        Login retrievedLoginFromSession = loginSession.getLogin();
        Assert.assertEquals(insertedLogin, retrievedLoginFromSession);
    }


}
