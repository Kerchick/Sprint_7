package steps;

import config.Configuration;
import io.qameta.allure.Step;
import model.AuthHelp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static io.restassured.RestAssured.given;

public class AuthSteps extends Configuration {

    @Step("Валидная авторизация")
    public static void validAuth(String login, String password) {

        AuthHelp auth = new AuthHelp();
        auth.Auth(login, password);

        courierId = given().log().all()
                .spec(sendHeader)
                .body(auth)
                .when()
                .post(url + "api/v1/courier/login")
                .then()
                .spec(ok200)
                .body("id", notNullValue())
                .extract().jsonPath().getString("id");

    }


    @Step("Невалидная авторизация")
    public static void invalidAuth(String login, String password, int expectedCode, String expectedMessage) {

        AuthHelp auth = new AuthHelp();
        auth.Auth(login, password);

        given().log().all()
                .spec(sendHeader)
                .body(auth)
                .when()
                .post(url + "api/v1/courier/login")
                .then()
                .body("code", equalTo(expectedCode))
                .body("message", equalTo(expectedMessage));

    }
}
