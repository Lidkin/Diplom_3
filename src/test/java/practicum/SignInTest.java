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
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SignInTest {

    String token, email, password, name;
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
        email = credentials.getEmail();
        password = credentials.getPassword();
        name = credentials.getName();
        token = userManipulation.registerOrLoginUser(body.UserRegisterBody(email, password, name), "register");
        browser = new Browser(myBrowser);
    }

    @After
    public void cleanUp(){
        browser.tearDown();
        userManipulation.deleteUser(token);
    }

    @Test
    public void byEnterAccountButtonTest(){
        MainPage mainPage = open(MainPage.pageUrl, MainPage.class);
        assertEquals("Войти в аккаунт", mainPage.getTextEnterAccountButton());
        String actual = mainPage
                .clickEnterToAccount()
                .login(email, password)
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byEnterProfileButtonTest(){
        MainPage mainPage = open(MainPage.pageUrl, MainPage.class);
        String actual = mainPage
                .clickEnterProfile()
                .login(email, password)
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void loginPageTest(){
        LoginPage loginPage = open(LoginPage.pageUrl, LoginPage.class);
        String actual = loginPage
                .login(email, password)
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byEnterProfileLoginPageTest(){
        LoginPage loginPage = open(LoginPage.pageUrl, LoginPage.class);
        String actual = loginPage
                .clickEnterProfile()
                .login(email, password)
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void registerPageTest(){
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        String actual = registerPage
                .clickLogin()
                .login(email, password)
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byEnterProfileRegisterPageTest(){
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        String actual = registerPage
                .clickEnterProfile()
                .login(email, password)
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void forgotPasswordPageTest(){
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.pageUrl, ForgotPasswordPage.class);
        String actual = forgotPasswordPage
                .clickLogin()
                .login(email, password)
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byEnterProfileForgotPasswordPageTest(){
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.pageUrl, ForgotPasswordPage.class);
        String actual = forgotPasswordPage
                .clickEnterProfile()
                .login(email, password)
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

}
