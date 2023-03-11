package registration;

import apiService.APIServices;
import com.github.javafaker.Faker;
import model.UserAccount;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import pages.LoginPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {
    private final Faker faker = new Faker(new Locale("en"));
    private final APIServices apiServices = new APIServices();
    private UserAccount account;
    private final List<UserAccount> testData = new ArrayList<>();

    @After
    public void cleanUp() {
        apiServices.deleteAccounts(testData);
    }

    @Test
    public void successRegistration(){
        account = new UserAccount().
                setEmail(faker.internet().emailAddress()).
                setPassword(faker.internet().password()).
                setName(faker.name().firstName());
        testData.add(account);
        open(RegistrationPage.URL);
        RegistrationPage regPage = new RegistrationPage();
        LoginPage loginPage = regPage.registrationAsNewClient(account.getName(), account.getEmail(), account.getPassword());
        assertTrue(loginPage.isDisplayed());
    }

    @Test
    public void registrationFiveCharPassReject(){
        account = new UserAccount().
                setEmail(faker.internet().emailAddress()).
                setPassword(RandomStringUtils.randomAlphanumeric(5)).
                setName(faker.name().firstName());
        testData.add(account);
        open(RegistrationPage.URL);
        RegistrationPage regPage = new RegistrationPage();
        regPage.registrationAsNewClient(account.getName(), account.getEmail(), account.getPassword());
        assertTrue(regPage.errorPasswordIsDisplayed());
        assertTrue(regPage.isDisplayed());
    }
}
