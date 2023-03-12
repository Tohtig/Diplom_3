package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site";
    private final SelenideElement loginButton = $x(".//button[text()='Войти в аккаунт']");
    private final SelenideElement builderTitle = $x(".//h1[text()='Соберите бургер']");
    private final SelenideElement createOrderButton = $x(".//button[text()='Оформить заказ']");
    private final SelenideElement bunHeader = $x(".//span[text()='Булки']");
    private final SelenideElement sauceHeader = $x(".//span[text()='Соусы']");
    private final SelenideElement fillingHeader = $x(".//span[text()='Начинки']");

    public MainPage() {
        if (!this.isDisplayed()) {
            throw new IllegalStateException("This is not MainPage");
        }
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage();
    }

    public boolean isDisplayed() {
        builderTitle.shouldBe(Condition.visible);
        return builderTitle.isDisplayed();
    }

    public boolean isAuthorisedUser() {
        createOrderButton.shouldBe(Condition.visible);
        return createOrderButton.isDisplayed();
    }

    public void clickBunHeader() {
        bunHeader.parent().click();
    }

    public void clickSauceHeader() {
        sauceHeader.parent().click();
    }

    public void clickFillingHeader() {
        fillingHeader.parent().click();
    }

    public boolean bunSectionSelected() {
        bunHeader.parent().shouldHave(attributeMatching("class", ".*current.*"));
        return bunHeader.parent().getAttribute("class").contains("_current__");
    }

    public boolean sauceSectionSelected() {
        sauceHeader.parent().shouldHave(attributeMatching("class", ".*current.*"));
        return sauceHeader.parent().getAttribute("class").contains("_current__");
    }

    public boolean fillingSectionSelected() {
        fillingHeader.parent().shouldHave(attributeMatching("class", ".*current.*"));
        return fillingHeader.parent().getAttribute("class").contains("_current__");
    }

}
