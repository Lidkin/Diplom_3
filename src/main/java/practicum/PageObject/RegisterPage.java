package practicum.PageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;


public class RegisterPage {

    public static final String pageUrl = "https://stellarburgers.nomoreparties.site/register";

    @FindAll({@FindBy(xpath = "//input")})
    protected ElementsCollection registerPageForm;

    @FindBy(how = How.XPATH, using = ".//a[text() = 'Войти']")
    protected SelenideElement signIn;

    @FindBy(how = How.XPATH, using = ".//button[text() = 'Зарегистрироваться']")
    protected SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//p[contains(text(), 'Некорректный пароль')]")
    protected SelenideElement errorMessage;

    @FindBy(how = How.XPATH, using = ".//h2[text()] = 'Регистрация")
    protected SelenideElement text;

    @FindBy(how = How.XPATH , using = ".//a[@href ='/account']")
    protected SelenideElement accountProfile;

    public RegisterPage enterName(String name) {
        registerPageForm.get(0).sendKeys(name);
        return this;
    }

    public RegisterPage enterEmail(String email) {
        registerPageForm.get(1).sendKeys(email);
        return this;
    }

    public RegisterPage enterPassword(String password){
        registerPageForm.get(2).sendKeys(password);
        return this;
    }

    @Step("press on \"login\"")
    public LoginPage clickLogin() {
        signIn.click();
        return page(LoginPage.class);
    }

    @Step("press on \"register\" and jump to login page")
    public LoginPage clickRegisterButton() {
        registerButton.click();
        return page(LoginPage.class);
    }

    @Step("press on \"register\"")
    public RegisterPage register() {
        registerButton.click();
        return this;
    }

    @Step
    public Boolean isErrorMessageDisplayed() throws InterruptedException {
        Thread.sleep(500);
        return errorMessage.isDisplayed();
    }

    @Step("press on \"account profile\"")
    public LoginPage clickEnterProfile() {
        accountProfile.click();
        return page(LoginPage.class);
    }

    @Step("press on \"account profile\" - authorized user, jump to account profile page")
    public AccountProfilePage clickEnterProfileAuthorizedUser(){
        accountProfile.click();
        return page(AccountProfilePage.class);
    }

}
