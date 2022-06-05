package practicum;

import io.qameta.allure.Step;
import practicum.PageObject.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;


public class UserManipulation {

    private final String baseURI = MainPage.pageUrl;

    @Step("create user and get token")
    protected String registerUserAndGetToken(Object body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(baseURI + "/api/auth/register")
                .body().path("accessToken").toString().substring(7);
    }

    @Step("delete user")
    protected void deleteUser(String token) {
        given()
                .auth().oauth2(token)
                .delete( baseURI + "/api/auth/user")
                .then()
                .assertThat().statusCode(202);
    }

    @Step("login user")
    protected MainPage login(String email, String password) throws InterruptedException {
        MainPage mainPage = open(MainPage.pageUrl, MainPage.class);
        return mainPage
                .clickEnterProfile()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin();
    }

}
