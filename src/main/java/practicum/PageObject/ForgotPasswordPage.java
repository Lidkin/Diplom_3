package practicum.PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;


public class ForgotPasswordPage {

    public static final String pageUrl = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Войти')]")
    protected SelenideElement signInButton;

    @FindBy(how = How.XPATH , using = ".//a[@href ='/account']")
    protected SelenideElement accountProfile;

    @Step("press \"enter\"")
    public LoginPage clickLogin(){
        signInButton.click();
        return page(LoginPage.class);
    }

    @Step("press \"account profile\"")
    public LoginPage clickEnterProfile(){
        accountProfile.click();
        return page(LoginPage.class);
    }

    @Step("press \"account profile\" - authorized user")
    public AccountProfilePage clickEnterProfileAuthorizedUser(){
        accountProfile.click();
        return page(AccountProfilePage.class);
    }

}
