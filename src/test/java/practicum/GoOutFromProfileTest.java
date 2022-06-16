package practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.pageobject.MainPage;
import practicum.user.Credentials;
import practicum.user.UserBody;
import practicum.user.UserManipulation;
import static com.codeborne.selenide.Selenide.localStorage;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GoOutFromProfileTest {

    String registerToken, token, email, password, name;
    MainPage mainPage;
    Credentials credentials = new Credentials();
    UserManipulation userManipulation = new UserManipulation();
    UserBody body = new UserBody();
    Browser browser;

    @Before
    public void registerUser(){
        browser = new Browser(myBrowser);
        email = credentials.getEmail();
        password = credentials.getPassword();
        name = credentials.getName();
        registerToken = userManipulation.registerOrLoginUser(body.UserRegisterBody(email, password, name), "register");
        mainPage = userManipulation.loginUserOnLoginPage(email, password);

    }

    @After
    public void cleanUp(){
        token = localStorage().getItem("accessToken");
        if (token == null) {
            userManipulation.deleteUser(registerToken);
        } else { userManipulation.deleteUser(token.substring(7)); }
        browser.tearDown();
    }

    @Parameterized.Parameter
    public String myBrowser;

    @Parameterized.Parameters(name = "in {0} browser")
    public static Object[][] browsers() {
        return new Object[][] {
                {"yandex"},
                {"google"}
        };
    }

    @Test
    public void fromMainPageTest(){
        String actual = mainPage
                .clickEnterProfileAuthorizedUser()
                .clickExitButton()
                .checkVisibility();
        assertEquals("Вход", actual);
    }
}
