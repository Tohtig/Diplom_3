package registration;

import com.github.javafaker.Faker;
import model.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AppHeaderPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegistrationPage;
import service.APIServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest2 extends WebDriverParams {
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
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginFromMainPageByLoginButton() {
        open(MainPage.URL);
        MainPage mainPage = new MainPage().clickLoginButton().login(account.getEmail(), account.getPassword());
        assertTrue(mainPage.isAuthorisedUser());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginFromMainPageByProfileButton() {
        open(MainPage.URL);
        MainPage mainPage = new AppHeaderPage().clickProfileButtonByUnauthorizedUser().login(account.getEmail(), account.getPassword());
        assertTrue(mainPage.isAuthorisedUser());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegistrationPageByLoginButton() {
        open(RegistrationPage.URL);
        MainPage mainPage = new RegistrationPage().loginButtonClick().login(account.getEmail(), account.getPassword());
        assertTrue(mainPage.isAuthorisedUser());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromPasswordRecoveryPageByLoginButton() {
        open(PasswordRecoveryPage.URL);
        MainPage mainPage = new PasswordRecoveryPage().loginButtonClick().login(account.getEmail(), account.getPassword());
        assertTrue(mainPage.isAuthorisedUser());
    }
}
