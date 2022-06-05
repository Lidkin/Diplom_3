package practicum.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.screenshot;


public class MainPage {

    public static final String pageUrl = "https://stellarburgers.nomoreparties.site";

    @FindBy(how = How.XPATH , using = ".//a[@href ='/account']")
    protected SelenideElement accountProfile;

    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Войти в аккаунт')]")
    protected SelenideElement enterAccountButton;

    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Оформить заказ')]")
    protected SelenideElement orderButton;

    @FindBy(how = How.XPATH, using = ".//div[contains(@class,'tab_tab__1SPyG')]")
    protected ElementsCollection ingredientsSection;

    @Step("press \"account profile\", jump to LoginPage")
    public LoginPage clickEnterProfile() {
        accountProfile.click();
        return page(LoginPage.class);
    }

    @Step("press \"account profile\" - authorized user, jump to AccountProfilePage")
    public AccountProfilePage clickEnterProfileAuthorizedUser(){
        accountProfile.click();
        return page(AccountProfilePage.class);
    }

    @Step("press \"enter to account\", jump to LoginPage")
    public LoginPage clickEnterToAccount(){
        enterAccountButton.click();
        return page(LoginPage.class);
    }

    @Step("text should be equal \"Войти в аккаунт\"")
    public String getTextEnterAccountButton() {
        return enterAccountButton.shouldBe(Condition.visible).getText();
    }

    @Step("text should be equal \"Оформить заказ\"")
    public String getTextOrderButton() {
        return orderButton.shouldBe(Condition.visible).getText();
    }

    @Step("selection of ingredients and screenshots")
    public MainPage selectIngredient(int index, String screenshotName) throws InterruptedException {
        if (index == 0)
            ingredientsSection.get(index + 2).shouldBe(Condition.enabled).click();
        ingredientsSection.get(index).click();
        Thread.sleep(500);
        Configuration.reportsFolder = "test-result/reports";
        String pngFileName = screenshot(screenshotName);
        return this;
    }

    @Step
    public String checkValue(int index) {
        return ingredientsSection
                .get(index).shouldBe(Condition.visible)
                .toWebElement()
                .getAttribute("class");
    }

}
