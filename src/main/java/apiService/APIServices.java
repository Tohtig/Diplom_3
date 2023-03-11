package apiService;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserAccount;
import org.apache.http.HttpStatus;

import java.util.List;

public class APIServices extends BaseHttpClient {
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/api";
//    private final String mock = "http://localhost:8082/api";

    @Step("Авторизация пользователя в системе")
    public ValidatableResponse login(UserAccount account) {
        User body = new User(account);
        return doPostRequest(baseUrl + "/auth/login", body);
    }

    @Step("Создание пользователя")
    public ValidatableResponse createAccount(UserAccount account) {
        return doPostRequest(baseUrl + "/auth/register", account);
    }

    @Step("Удаление пользователей")
    public void deleteAccounts(List<UserAccount> accounts) {
        ValidatableResponse loginResp;
        if (!accounts.isEmpty()) {
            for (UserAccount account : accounts) {
                loginResp = login(account);
                if (loginResp.extract().statusCode() == HttpStatus.SC_OK) {
                    doDeleteRequest(baseUrl + "/auth/user", loginResp.extract().body().jsonPath().getString("accessToken").substring(7));
                }
            }
        }
    }
}
