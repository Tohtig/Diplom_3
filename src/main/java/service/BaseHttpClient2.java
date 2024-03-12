package service;

import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BaseHttpClient2 {
    private final String JSON = "application/json";

    private final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true));

    protected ValidatableResponse doPostRequest(String uri, Object body) {
        return given().config(config)
                .header("Content-Type", JSON)
                .body(body)
                .post(uri).then();
    }

    protected void doDeleteRequest(String uri, String accessToken) {
        given().config(config)
                .header("Content-Type", JSON)
                .auth().oauth2(accessToken)
                .get(uri);
    }
}
