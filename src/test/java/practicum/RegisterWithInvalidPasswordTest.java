package practicum;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.pageobject.RegisterPage;
import practicum.user.UserManipulation;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegisterWithInvalidPasswordTest {

    Browser browser;
    static String validPassword = RandomStringUtils.randomAlphabetic(6);
    UserManipulation userManipulation = new UserManipulation();

    @Parameterized.Parameter
    public String password;

    @Parameterized.Parameter(1)
    public int passwordLength;

    @Parameterized.Parameter(2)
    public Boolean expected;

    @Parameterized.Parameter(3)
    public String message;

    @Parameterized.Parameters (name = "password \"{0}\" length {1} symbols {3}")
    public static Object[][] testData(){
        return new Object[][]{
                {validPassword, validPassword.length(), false, "is valid"},
                {validPassword.substring(0, 5), validPassword.substring(0, 5).length(), true, "is invalid"},
                {validPassword.substring(0, 4), validPassword.substring(0, 4).length(), true, "is invalid"},
                {validPassword.substring(0, 3), validPassword.substring(0, 3).length(), true, "is invalid"},
                {validPassword.substring(0, 2), validPassword.substring(0, 2).length(), true, "is invalid"},
                {validPassword.substring(0, 1), validPassword.substring(0, 1).length(), true, "is invalid"},
        };
    }

    @After
    public void closeWindow() {
        String token = localStorage().getItem("accessToken");
        if(token != null)
            userManipulation.deleteUser(token.substring(7));
        browser.tearDown();
    }

    @Test
    public void registerInGoogleTest(){
        browser = new Browser("google");
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        Boolean actual = registerPage
                .register(password)
                .isErrorMessageDisplayed();
        assertEquals(expected, actual);
    }

    @Test
    public  void registerInYandexTest(){
        browser = new Browser("yandex");
        RegisterPage registerPage = open(RegisterPage.pageUrl, RegisterPage.class);
        Boolean actual = registerPage
                .register(password)
                .isErrorMessageDisplayed();
        assertEquals(expected, actual);
    }

}
