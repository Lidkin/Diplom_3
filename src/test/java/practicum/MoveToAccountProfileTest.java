package practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.pageobject.ForgotPasswordPage;
import practicum.pageobject.LoginPage;
import practicum.pageobject.MainPage;
import practicum.pageobject.RegisterPage;
import practicum.user.Credentials;
import practicum.user.UserBody;
import practicum.user.UserManipulation;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MoveToAccountProfileTest {

    String registerToken, token, email, password, name;
    Browser browser;
    UserBody body = new UserBody();
    UserManipulation userManipulation = new UserManipulation();
    Credentials credentials = new Credentials();

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
        browser = new Browser(myBrowser);
        email = credentials.getEmail();
        password = credentials.getPassword();
        name = credentials.getName();
        registerToken = userManipulation.registerOrLoginUser(body.UserRegisterBody(email, password, name), "register");
    }

    @After
    public void cleanUp(){
        token = localStorage().getItem("accessToken");
        if(token == null){
            userManipulation.deleteUser(registerToken);
        } else { userManipulation.deleteUser(token.substring(7)); }
        browser.tearDown();
    }

    @Test
    public void fromMainPageTest(){
        MainPage mainPage = userManipulation.loginUserOnLoginPage(email, password);
        Boolean actual = mainPage
                .clickEnterProfileAuthorizedUser()
                .saveButtonIsDisplayed();
        assertTrue(actual);
    }

    @Test
    public void fromLoginPageTest(){
        LoginPage loginPage = open(LoginPage.pageUrl, LoginPage.class);
        Boolean actual = loginPage
                .clickEnterProfile()
                .login(email,password)
                .clickEnterProfileAuthorizedUser()
                .saveButtonIsDisplayed();
        assertTrue(actual);
    }

    @Test
    public void fromRegisterPageTest(){
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        Boolean actual = registerPage
                .clickEnterProfileAuthorizedUser()
                .login(email, password)
                .clickEnterProfileAuthorizedUser()
                .saveButtonIsDisplayed();
        assertTrue(actual);
    }

    @Test
    public void fromForgotPasswordPageTest(){
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.pageUrl, ForgotPasswordPage.class);
        Boolean actual = forgotPasswordPage
                .clickEnterProfileAuthorizedUser()
                .login(email, password)
                .clickEnterProfileAuthorizedUser()
                .saveButtonIsDisplayed();
        assertTrue(actual);
    }

}
