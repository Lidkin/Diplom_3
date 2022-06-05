package practicum.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public static final String pageUrl = "https://stellarburgers.nomoreparties.site/login";

    @FindAll({@FindBy(xpath = "//input")})
    protected ElementsCollection loginPageForm;

    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Войти')]")
    protected SelenideElement signInButton;

    @FindBy(how = How.XPATH , using = ".//a[@href ='/account']")
    protected SelenideElement accountProfile;

    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Вход')]")
    protected SelenideElement text;

    @Step("press \"account profile\"")
    public LoginPage clickEnterProfile() {
        accountProfile.click();
        return this;
    }

    @Step("press \"account profile\" - authorized user")
    public AccountProfilePage clickEnterProfileAuthorizedUser(){
        accountProfile.click();
        return page(AccountProfilePage.class);
    }

    @Step("press on \"login\"")
    public MainPage clickLogin() {
        signInButton.click();
        return page(MainPage.class);
    }

    @Step
    public LoginPage enterEmail(String email) throws InterruptedException {
        Thread.sleep(500);
        loginPageForm.get(0).sendKeys(email);
        return this;
    }

    @Step
    public LoginPage enterPassword(String password) {
        loginPageForm.get(1).sendKeys(password);
        return this;
    }

    @Step("check visibility \"Вход\"")
        public String checkVisibility() {
            return text.shouldBe(Condition.visible).getText();
    }

}
