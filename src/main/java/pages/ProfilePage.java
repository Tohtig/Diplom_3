package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final SelenideElement profilePageTitle = $x(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final SelenideElement exitButton = $x(".//button[text()='Выход']");

    public ProfilePage() {
        if (!this.isDisplayed()) {
            throw new IllegalStateException("This is not ProfilePage");
        }
    }

    @Step("клик на кнопку 'Выход' со страницы профиля")
    public LoginPage exitButtonClick() {
        exitButton.click();
        return new LoginPage();
    }

    @Step("Проверка что открыта страница профиля")
    public boolean isDisplayed() {
        profilePageTitle.shouldBe(Condition.visible);
        return profilePageTitle.isDisplayed();
    }
}
