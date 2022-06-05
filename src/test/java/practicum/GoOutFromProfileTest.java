package practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GoOutFromProfileTest {

    String email = "lisha66@yandex.com";
    String password = "kokoko-111";
    String name = "Lidkin";
    String token;

    UserManipulation userManipulation = new UserManipulation();

    @Before
    public void registerUser(){
        User body = new User(email,password,name);
        token = userManipulation.registerUserAndGetToken(body);
    }

    @After
    public void cleanUp(){
        userManipulation.deleteUser(token);
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
    public void moveFromMainPageTest() throws InterruptedException {
        Browser browser = new Browser(myBrowser);
        //MainPage mainPage = open(MainPage.pageUrl, MainPage.class);
        String actual = userManipulation.login(email, password)
                .clickEnterProfileAuthorizedUser()
                .clickExitButton()
                .checkVisibility();
        assertEquals("Вход", actual);
        browser.tearDown();
    }
}
