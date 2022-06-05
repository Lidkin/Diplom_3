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
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SignInTest {

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
    public void byEnterAccountButtonTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        MainPage mainPage = open(MainPage.pageUrl, MainPage.class);
        assertEquals("Войти в аккаунт", mainPage.getTextEnterAccountButton());
        String actual = mainPage
                .clickEnterToAccount()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byEnterProfileButtonTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        MainPage mainPage = open(MainPage.pageUrl, MainPage.class);
        String actual = mainPage
                .clickEnterProfile()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void loginPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        LoginPage loginPage = open(LoginPage.pageUrl, LoginPage.class);
        String actual = loginPage
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byEnterProfileLoginPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        LoginPage loginPage = open(LoginPage.pageUrl, LoginPage.class);
        String actual = loginPage
                .clickEnterProfile()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void registerPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        String actual = registerPage
                .clickLogin()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byEnterProfileRegisterPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        String actual = registerPage
                .clickEnterProfile()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void forgotPasswordPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.pageUrl, ForgotPasswordPage.class);
        String actual = forgotPasswordPage
                .clickLogin()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byEnterProfileForgotPasswordPageTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.pageUrl, ForgotPasswordPage.class);
        String actual = forgotPasswordPage
                .clickEnterProfile()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

}
