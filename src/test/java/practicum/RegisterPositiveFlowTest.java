package practicum;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.PageObject.RegisterPage;
import java.util.Objects;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegisterPositiveFlowTest {

    String email = "lisha66@yandex.com";
    String password = "kokoko-111";
    String name = "Lidkin";
    String token;
    Browser browser;

    @Parameterized.Parameter
    public String myBrowser;

    @Parameterized.Parameters(name = "in {0} browser")
    public static Object[][] browsers() {
        return new Object[][] {
                {"yandex"},
                {"google"}
        };
    }

    @After
    public void cleanUp() {
        browser.tearDown();
        UserManipulation userManipulation = new UserManipulation();
        userManipulation.deleteUser(token);
    }

    @Test
    public void registerTest() throws InterruptedException {
        browser = new Browser(myBrowser);
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        String actual = registerPage
                .enterEmail(email)
                .enterName(name)
                .enterPassword(password)
                .clickRegisterButton()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .getTextOrderButton();
        token = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        assertEquals("Оформить заказ", actual);
    }

}
