package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AppHeaderPage {
    private final SelenideElement profileButton = $x(".//p[text()='Личный Кабинет']");
    private final SelenideElement builderButton = $x(".//p[text()='Конструктор']");
    private final SelenideElement appLogo = $x(".//div[@class='AppHeader_header__logo__2D0X2']");

    public LoginPage clickProfileButtonByUnauthorizedUser() {
        profileButton.click();
        return new LoginPage();
    }

    public ProfilePage clickProfileButtonByAuthorizedUser() {
        profileButton.click();
        return new ProfilePage();
    }

    public MainPage clickBuilderButton() {
        builderButton.click();
        return new MainPage();
    }

    public MainPage clickAppLogo() {
        appLogo.click();
        return new MainPage();
    }
}
