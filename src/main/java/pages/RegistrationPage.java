package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site/register";
    private final SelenideElement usernameField = $x("//div[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final SelenideElement emailField = $x("//div[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final SelenideElement passwordField = $x("//div[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    private final SelenideElement registrationButton = $x(".//button[text()='Зарегистрироваться']");
    private final SelenideElement loginButton = $x(".//a[@href='/login'][text()='Войти']");
    private final SelenideElement errorPassword = $x(".//p[text()='Некорректный пароль']");

    public RegistrationPage() {
        if (!this.isDisplayed()) {
            throw new IllegalStateException("This is not RegistrationPage");
        }
    }

    @Step("заполнить поля для регистрации пользователя с ожидаемой успешной регистрацией")
    public LoginPage registrationAsNewClient(final String username, final String email, final String password) {
        usernameField.setValue(username);
        emailField.setValue(email);
        passwordField.sendKeys(password);
        registrationButton.click();
        return new LoginPage();
    }

    @Step("заполнить поля для регистрации пользователя с ожидаемой ошибкой")
    public RegistrationPage registrationUnsuccess(final String username, final String email, final String password) {
        usernameField.setValue(username);
        emailField.setValue(email);
        passwordField.sendKeys(password);
        registrationButton.click();
        return this;
    }

    @Step("клик на кнопку 'Войти'")
    public LoginPage loginButtonClick() {
        loginButton.click();
        return new LoginPage();
    }

    @Step("проверка что отображается страница регистрации")
    public boolean isDisplayed() {
        registrationButton.shouldBe(Condition.visible);
        return registrationButton.isDisplayed();
    }

    @Step("проверка что отображается ошибка 'Некорректный пароль'")
    public boolean errorPasswordIsDisplayed() {
        errorPassword.shouldBe(Condition.visible);
        return errorPassword.isDisplayed();
    }
}
