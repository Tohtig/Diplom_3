package registration;

import service.APIServices;
import com.github.javafaker.Faker;
import model.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AppHeaderPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest extends WebDriverParams {
    private final Faker faker = new Faker(new Locale("en"));
    private final APIServices apiServices = new APIServices();
    private final List<UserAccount> testData = new ArrayList<>();
    private UserAccount account;

    @BeforeEach
    public void setUp() {
        account = new UserAccount().
                setEmail(faker.internet().emailAddress()).
                setPassword(faker.internet().password()).
                setName(faker.name().firstName());
        testData.add(account);
        apiServices.createAccount(account);
    }

    @AfterEach
    public void cleanUp() {
        apiServices.deleteAccounts(testData);
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет по клику на \"Личный кабинет\"")
    public void checkEnterProfileFromAppHeader() {
        open(MainPage.URL);
        new AppHeaderPage().clickProfileButtonByUnauthorizedUser().login(account.getEmail(), account.getPassword());
        ProfilePage profilePage = new AppHeaderPage().clickProfileButtonByAuthorizedUser();
        assertTrue(profilePage.isDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор. Проверка перехода по клику на \"Конструктор\"")
    public void checkMovingFromProfileToBurgerBuilder() {
        open(MainPage.URL);
        new AppHeaderPage().clickProfileButtonByUnauthorizedUser().login(account.getEmail(), account.getPassword());
        MainPage mainPage = new AppHeaderPage().clickBuilderButton();
        assertTrue(mainPage.isDisplayed());
        assertTrue(mainPage.isAuthorisedUser());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор. Проверка перехода по клику на логотип \"Stellar Burgers\"")
    public void checkMovingFromProfileToLogo() {
        open(MainPage.URL);
        new AppHeaderPage().clickProfileButtonByUnauthorizedUser().login(account.getEmail(), account.getPassword());
        MainPage mainPage = new AppHeaderPage().clickAppLogo();
        assertTrue(mainPage.isDisplayed());
        assertTrue(mainPage.isAuthorisedUser());
    }

    @Test
    @DisplayName("Выход из аккаунта. Проверка выхода по кнопке \"Выйти\" в личном кабинете")
    public void checkExitByExitButtonFromProfile() {
        open(MainPage.URL);
        new AppHeaderPage().clickProfileButtonByUnauthorizedUser().login(account.getEmail(), account.getPassword());
        ProfilePage profilePage = new AppHeaderPage().clickProfileButtonByAuthorizedUser();
        LoginPage loginPage = profilePage.exitButtonClick();
        assertTrue(loginPage.isDisplayed());
    }
}
