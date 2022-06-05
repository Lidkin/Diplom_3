package practicum.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;


public class AccountProfilePage {

    public static final String pageUrl = "https://stellarburgers.nomoreparties.site/account/profile";

    @FindBy(how = How.XPATH, using = ".//p[contains(text(), 'Конструктор')]/..")
    protected SelenideElement constructorButton;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    protected SelenideElement logoButton;

    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Выход')]")
    protected SelenideElement exitButton;

    @FindBy(how = How.CLASS_NAME, using = "button_button_type_primary__1O7Bx")
    protected SelenideElement saveButton;

    @Step("press on \"Constructor\"")
    public MainPage clickConstructorButton() {
        constructorButton.click();
        return page(MainPage.class);
    }

    @Step("press on logo")
    public MainPage clickLogoButton() {
        logoButton.click();
        return page(MainPage.class);
    }

    @Step("press \"exit\"")
    public LoginPage clickExitButton() {
        exitButton.click();
        return page(LoginPage.class);
    }

    @Step
    public AccountProfilePage checkIsAccountProfilePage() {
        saveButton.shouldBe(Condition.exactText("Сохранить"));
        return this;
    }

    @Step
    public Boolean saveButtonIsDisplayed() {
        return saveButton.shouldBe(Condition.visible).isDisplayed();
    }

}
