package registration;
import apiService.APIServices;
import com.github.javafaker.Faker;
import model.UserAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegistrationPage;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    private final Faker faker = new Faker(new Locale("en"));
    private final APIServices apiServices = new APIServices();
    private UserAccount account;
    private final List<UserAccount> testData = new ArrayList<>();


    @Before
    public void setUp() {
        account = new UserAccount().
                setEmail(faker.internet().emailAddress()).
                setPassword(faker.internet().password()).
                setName(faker.name().firstName());
        testData.add(account);
        apiServices.createAccount(account);
    }

    @After
    public void cleanUp() {
        apiServices.deleteAccounts(testData);
    }

    @Test
    public void loginFromMainPageByLoginButton(){
        open(MainPage.URL);
        MainPage mainPage = new MainPage().clickLoginButton().login(account.getEmail(), account.getPassword());
        assertTrue(mainPage.isAuthorisedUser());
    }

    @Test
    public void loginFromMainPageByProfileButton(){
        open(MainPage.URL);
        MainPage mainPage = new MainPage().clickProfileButton().login(account.getEmail(), account.getPassword());
        assertTrue(mainPage.isAuthorisedUser());
    }

    @Test
    public void loginFromRegistrationPageByLoginButton(){
        open(RegistrationPage.URL);
        MainPage mainPage = new RegistrationPage().loginButtonClick().login(account.getEmail(), account.getPassword());
        assertTrue(mainPage.isAuthorisedUser());
    }

    @Test
    public void loginFromPasswordRecoveryPageByLoginButton(){
        open(PasswordRecoveryPage.URL);
        MainPage mainPage = new PasswordRecoveryPage().loginButtonClick().login(account.getEmail(), account.getPassword());
        assertTrue(mainPage.isAuthorisedUser());
    }
}
