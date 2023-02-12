package steps;

import config.Configuration;
import io.qameta.allure.Step;
import model.AuthHelp;
import model.RegistrationCourierHelp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RegistrationCourierSteps extends Configuration {

    @Step("Регистрация курьера с валидными данными")
    public static void registerValidCourier(String login, String password, String firstName) {

        RegistrationCourierHelp auth = new RegistrationCourierHelp();
        auth.Registration(login, password, firstName);

        courierId = given().log().all()
                .spec(sendHeader)
                .body(auth)
                .when()
                .post(url + "api/v1/courier/")
                .then()
                .spec(ok201)
                .body("ok", notNullValue())
                .extract().jsonPath().getString("id");
    }


    @Step("Регистрация курьера с невалидными данными")
    public static void registerInvalidCourier(String login, String password, String firstName, int expectedCode, String expectedMessage) {

        RegistrationCourierHelp auth = new RegistrationCourierHelp();
        auth.Registration(login, password, firstName);

        given().log().all()
                .spec(sendHeader)
                .body(auth)
                .when()
                .post(url + "api/v1/courier/")
                .then()
                .body("code", equalTo(expectedCode))
                .body("message", equalTo(expectedMessage));
    }

}
