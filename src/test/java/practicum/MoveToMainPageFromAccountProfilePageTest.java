package practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.PageObject.AccountProfilePage;
import practicum.PageObject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MoveToMainPageFromAccountProfilePageTest {

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
    public void registerUser() {
        User body = new User(email,password,name);
        token = userManipulation.registerUserAndGetToken(body);
    }

    @After
    public void cleanUp(){
        browser.tearDown();
        userManipulation.deleteUser(token);
    }

    @Test
    public void byConstructorButtonTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        AccountProfilePage accountProfilePage = userManipulation.login(email, password)
                .clickEnterProfileAuthorizedUser();
        String actual = accountProfilePage
                .checkIsAccountProfilePage()
                .clickConstructorButton()
                .getTextOrderButton();
         assertEquals("Оформить заказ", actual);
    }

    @Test
    public void byLogoButtonTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        AccountProfilePage accountProfilePage = userManipulation.login(email, password)
                .clickEnterProfileAuthorizedUser();
        String actual = accountProfilePage
                .checkIsAccountProfilePage()
                .clickLogoButton()
                .getTextOrderButton();
        assertEquals("Оформить заказ", actual);
    }

}
