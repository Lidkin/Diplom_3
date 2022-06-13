package practicum.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class LoginPage {

    public static final String pageUrl = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Войти')]")
    protected SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = ".//a[@href ='/account']")
    protected SelenideElement accountProfile;

    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Вход')]")
    protected SelenideElement text;

    @FindBy(how = How.XPATH, using = "//label[text() ='Пароль']/following-sibling::input")
    protected SelenideElement passwordValue;

    @FindBy(how = How.XPATH, using = "//label[text() ='Email']/following-sibling::input")
    protected SelenideElement emailValue;

    @Step("press \"account profile\"")
    public LoginPage clickEnterProfile() {
        accountProfile.click();
        return this;
    }

    @Step("press on \"login\" with wait mode")
    public MainPage loginWithWait(String email, String password, WebDriverWait wait) {
        wait.until(ExpectedConditions.urlToBe(pageUrl));
        setEmail(email);
        setPassword(password);
        clickSignInButton();
        return page(MainPage.class);
    }

    @Step("press on \"login\"")
    public MainPage login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickSignInButton();
        return page(MainPage.class);
    }

    public void setEmail(String email) {
        emailValue.shouldBe(interactable)
                .setValue(email);
    }

    public void setPassword(String password) {
        passwordValue.shouldBe(interactable)
                .setValue(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    @Step("check visibility \"Вход\"")
    public String checkVisibility() {
        return text.shouldBe(visible).getText();
    }

}
