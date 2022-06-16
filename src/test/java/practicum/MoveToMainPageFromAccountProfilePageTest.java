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
public class MoveToMainPageFromAccountProfilePageTest {

    String registerToken, token, email, password, name;
    Browser browser;
    MainPage mainPage;
    Credentials credentials = new Credentials();
    UserManipulation userManipulation = new UserManipulation();
    UserBody body = new UserBody();

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
    public void registerUser() {
        email = credentials.getEmail();
        password = credentials.getPassword();
        name = credentials.getName();
        registerToken = userManipulation.registerOrLoginUser(body.UserRegisterBody(email, password, name),"register");
        browser = new Browser(myBrowser);
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

    @Test
    public void byConstructorButtonTest(){
        String actual = mainPage
                .clickEnterProfileAuthorizedUser()
                .checkIsAccountProfilePage()
                .clickConstructorButton()
                .getTextOrderButton();
         assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byLogoButtonTest()  {
        String actual = mainPage
                .clickEnterProfileAuthorizedUser()
                .checkIsAccountProfilePage()
                .clickLogoButton()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

}
