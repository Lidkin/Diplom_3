package practicum.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class RegisterPage {

    public static final String pageUrl = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = ".//a[text() = 'Войти']")
    protected SelenideElement signIn;

    @FindBy(how = How.XPATH, using = ".//button[text() = 'Зарегистрироваться']")
    protected SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//p[contains(text(), 'Некорректный пароль')]")
    protected SelenideElement errorMessage;

    @FindBy(how = How.XPATH, using = ".//h2[text()] = 'Регистрация")
    protected SelenideElement text;

    @FindBy(how = How.XPATH, using = ".//a[@href ='/account']")
    protected SelenideElement accountProfile;

    @FindBy(how = How.XPATH, using = "//label[text() ='Пароль']/following-sibling::input")
    protected SelenideElement passwordValue;

    @FindBy(how = How.XPATH, using = "//label[text() ='Email']/following-sibling::input")
    protected SelenideElement emailValue;

    @FindBy(how = How.XPATH, using = "//label[text() = 'Имя']/following-sibling::input")
    protected SelenideElement nameValue;

    public void setName(String name) {
        nameValue.shouldBe(interactable)
                .setValue(name);
    }

    public void setEmail(String email) {
        emailValue.shouldBe(interactable)
                .setValue(email);
    }

    public void setPassword(String password) {
        passwordValue.shouldBe(interactable)
                .setValue(password);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("press on \"login\"")
    public LoginPage clickLogin() {
        signIn.click();
        return page(LoginPage.class);
    }

    @Step("press on \"register\" and jump to login page")
    public LoginPage registerUser(String email, String password, String name) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
        return page(LoginPage.class);
    }

    @Step("press on \"register\"")
    public RegisterPage register(String password) {
        setPassword(password);
        clickRegisterButton();
        return this;
    }

    @Step
    public Boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    @Step("press on \"account profile\"")
    public LoginPage clickEnterProfile() {
        accountProfile.click();
        return page(LoginPage.class);
    }

    @Step("press on \"account profile\" - authorized user, jump to account profile page")
    public AccountProfilePage clickEnterProfileAuthorizedUser() {
        accountProfile.click();
        return page(AccountProfilePage.class);
    }

}

