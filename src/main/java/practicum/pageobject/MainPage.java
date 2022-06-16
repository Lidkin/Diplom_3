package practicum.pageobject;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class MainPage {

    public static final String pageUrl = "https://stellarburgers.nomoreparties.site";

    protected SelenideElement accountProfile = $(byXpath("//a[@href = '/account']"));

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Войти в аккаунт')]")
    protected SelenideElement enterAccountButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Оформить заказ')]")
    protected SelenideElement orderButton;

    protected ElementsCollection ingredientsSection = $$(byCssSelector("div.noselect"));

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

    @Step("check right chosen after click to element of ingredients section")
    public String jumpsInSection(int index, String expectedValue, WebDriverWait wait, String name){
        SelenideElement section = ingredientsSection.get(index);
        wait.until(ExpectedConditions.elementToBeClickable(section.toWebElement())).isDisplayed();
        section.click();
        wait.until(ExpectedConditions.attributeContains(section.shouldHave(text(name)).toWebElement(),"class", expectedValue));
        return section.getAttribute("class");

    }

}
