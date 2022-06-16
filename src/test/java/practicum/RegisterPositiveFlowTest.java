package practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.pageobject.RegisterPage;
import practicum.user.Credentials;
import practicum.user.UserBody;
import practicum.user.UserManipulation;
import java.util.Objects;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegisterPositiveFlowTest {

    String token, email, password, name;
    UserBody body = new UserBody();
    WebDriverWait wait;
    Browser browser;
    Credentials credentials = new Credentials();
    UserManipulation userManipulation = new UserManipulation();
    @Parameterized.Parameter
    public String myBrowser;

    @Parameterized.Parameters(name = "in {0} browser")
    public static Object[][] browsers() {
        return new Object[][] {
                {"yandex"},
                {"google"}
        };
    }
    @Before
    public void setUp(){
        email = credentials.getEmail();
        password = credentials.getPassword();
        name = credentials.getName();
        browser = new Browser(myBrowser);
        wait = browser.getWaitObject();
    }

    @After
    public void cleanUp() {
        browser.tearDown();
        if (token != null) {
            userManipulation.deleteUser(token);
        } else {
            userManipulation.deleteUser(userManipulation.registerOrLoginUser(body.UserLoginBody(email, password), "login"));
        }
    }

    @Test
    public void registerTest(){
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        String actual = registerPage
                .registerUser(email, password, name)
                .loginWithWait(email, password, wait)
                .getTextOrderButton();
        token = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        assertEquals("Оформить заказ", actual);
    }

}
