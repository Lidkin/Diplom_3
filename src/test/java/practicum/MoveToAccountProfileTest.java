package practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.PageObject.ForgotPasswordPage;
import practicum.PageObject.LoginPage;
import practicum.PageObject.MainPage;
import practicum.PageObject.RegisterPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MoveToAccountProfileTest {

    String email = "lisha66@yandex.com";
    String password = "kokoko-111";
    String name = "Lidkin";
    String token;
    Browser browser;

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
    public void registerUser(){
        User body = new User(email,password,name);
        token = userManipulation.registerUserAndGetToken(body);
    }

    @After
    public void cleanUp(){
        browser.tearDown();
        userManipulation.deleteUser(token);
    }

    @Test
    public void fromMainPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        MainPage login = userManipulation.login(email, password);
        Boolean actual = login
                .clickEnterProfileAuthorizedUser()
                .saveButtonIsDisplayed();
        assertTrue(actual);
    }

    @Test
    public void fromLoginPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        userManipulation.login(email, password);
        LoginPage loginPage = open(LoginPage.pageUrl, LoginPage.class);
        Boolean actual = loginPage
                .clickEnterProfileAuthorizedUser()
                .saveButtonIsDisplayed();
        assertTrue(actual);
    }

    @Test
    public void fromRegisterPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        userManipulation.login(email, password);
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        Boolean actual = registerPage
                .clickEnterProfileAuthorizedUser()
                .saveButtonIsDisplayed();
        assertTrue(actual);
    }

    @Test
    public void fromForgotPasswordPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        userManipulation.login(email, password);
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.pageUrl, ForgotPasswordPage.class);
        Boolean actual = forgotPasswordPage
                .clickEnterProfileAuthorizedUser()
                .saveButtonIsDisplayed();
        assertTrue(actual);
    }

}
