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

    String registerToken, loginToken, email, password, name;
    Credentials credentials = new Credentials();
    UserManipulation userManipulation = new UserManipulation();
    UserBody body = new UserBody();
    Browser browser;

    @Before
    public void registerUser(){
        email = credentials.getEmail();
        password = credentials.getPassword();
        name = credentials.getName();
        registerToken = userManipulation.registerOrLoginUser(body.UserRegisterBody(email, password, name), "register");
        browser = new Browser(myBrowser);
    }

    @After
    public void cleanUp(){
        if (loginToken == null) {
            userManipulation.deleteUser(registerToken);
        } else { userManipulation.deleteUser(loginToken.substring(7)); }
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
        MainPage mainPage = userManipulation.loginUserOnLoginPage(email, password);
        loginToken = localStorage().getItem("accessToken");
        String actual = mainPage
                .clickEnterProfileAuthorizedUser()
                .clickExitButton()
                .checkVisibility();
        assertEquals("Вход", actual);
    }
}
