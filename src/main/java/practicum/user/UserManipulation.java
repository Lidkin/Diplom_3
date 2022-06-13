package practicum.user;

import io.qameta.allure.Step;
import practicum.pageobject.LoginPage;
import practicum.pageobject.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;


public class UserManipulation {

    private final String baseURI = MainPage.pageUrl;

    @Step("register/login user and get token")
    public String registerOrLoginUser(String body, String endpoint) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(baseURI + "/api/auth/" + endpoint)
                .body().path("accessToken").toString().substring(7);
    }

    @Step("delete user")
    public void deleteUser(String token) {
        given()
                .auth().oauth2(token)
                .delete( baseURI + "/api/auth/user")
                .then()
                .assertThat().statusCode(202);
    }

    @Step("login user")
    public MainPage loginUserOnLoginPage(String email, String password) {
        LoginPage loginPage = open(LoginPage.pageUrl, LoginPage.class);
        return loginPage.login(email, password);
    }

}
