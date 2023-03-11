package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PasswordRecoveryPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final SelenideElement passRecoveryPageTitle = $x(".//h2[text()='Восстановление пароля']");
    private final SelenideElement loginButton = $x(".//a[@href='/login'][text()='Войти']");

    public PasswordRecoveryPage() {
        if (!this.isDisplayed()) {
            throw new IllegalStateException("This is not PasswordRecoveryPage");
        }
    }

    public LoginPage loginButtonClick() {
        loginButton.click();
        return new LoginPage();
    }

    public boolean isDisplayed() {
        passRecoveryPageTitle.shouldBe(Condition.visible);
        return passRecoveryPageTitle.isDisplayed();
    }
}