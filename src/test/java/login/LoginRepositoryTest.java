package login;

import com.realdolmen.domain.Enums;
import com.realdolmen.domain.login.Login;
import com.realdolmen.domain.login.LoginRepository;
import org.junit.Test;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
public class LoginRepositoryTest {

    LoginRepository loginRepository = new LoginRepository();

    @Test
    public void getUserWithGivenNameAndPassword(){
        Login logInToBeRetrieved = new Login("admin","admin", Enums.Roles.ADMIN);
        loginRepository.retrievePersonWithGivenNameAndPassword(logInToBeRetrieved);
    }
}
