package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AppHeaderPage {
    private final SelenideElement profileButton = $x(".//p[text()='Личный Кабинет']");
    private final SelenideElement builderButton = $x(".//p[text()='Конструктор']");
    private final SelenideElement appLogo = $x(".//div[@class='AppHeader_header__logo__2D0X2']");

    @Step("клик на профиль для неавторизованного пользователя")
    public LoginPage clickProfileButtonByUnauthorizedUser() {
        profileButton.click();
        return new LoginPage();
    }

    @Step("клик на профиль для авторизованного пользователя")
    public ProfilePage clickProfileButtonByAuthorizedUser() {
        profileButton.click();
        return new ProfilePage();
    }

    @Step("клик на 'Конструктор' в AppHeader")
    public MainPage clickBuilderButton() {
        builderButton.click();
        return new MainPage();
    }

    @Step("клик на лого приложения")
    public MainPage clickAppLogo() {
        appLogo.click();
        return new MainPage();
    }
}
