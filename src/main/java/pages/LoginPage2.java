package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage2 {
    private final SelenideElement emailField = $("input[name='name']");
    private final SelenideElement passwordField = $("input[name='Пароль']");
    private final SelenideElement loginButton = $x(".//button[text()='Войти']");

    public LoginPage2() {
        if (!this.isDisplayed()) {
            throw new IllegalStateException("This is not LoginPage");
        }
    }

    @Step("заполнить поля для авторизации пользователя")
    public MainPage login(final String email, final String password) {
        emailField.setValue(email);
        passwordField.sendKeys(password);
        loginButton.click();
        return new MainPage();
    }

    @Step("проверка что видна кнопка 'Вход' loginPage")
    public boolean isDisplayed() {
        loginButton.shouldBe(Condition.visible);
        return loginButton.isDisplayed();
    }
}
