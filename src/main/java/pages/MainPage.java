package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site";
    private final SelenideElement loginButton = $x(".//button[text()='Войти в аккаунт']");
    private final SelenideElement profileButton = $x(".//p[text()='Личный Кабинет']");
    private final SelenideElement mainPageTitle = $x(".//h1[text()='Соберите бургер']");
    private final SelenideElement createOrderButton = $x(".//button[text()='Оформить заказ']");

    public MainPage() {
        if (!this.isDisplayed()) {
            throw new IllegalStateException("This is not MainPage");
        }
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage();
    }

    public LoginPage clickProfileButton() {
        profileButton.click();
        return new LoginPage();
    }

    public boolean isDisplayed() {
        mainPageTitle.shouldBe(Condition.visible);
        return mainPageTitle.isDisplayed();
    }

    public boolean isAuthorisedUser() {
        createOrderButton.shouldBe(Condition.visible);
        return createOrderButton.isDisplayed();
    }
}
