package steps;

import config.Configuration;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class DeleteCourierSteps extends Configuration {
    final static String apiV1Courier = "api/v1/courier/";

    @Step("Удаление курьера с валидным данными")
    public static void deleteValidCourier(String courierId) {

        given().log().all()
                .spec(sendHeader)
                .when()
                .delete(url + apiV1Courier + courierId)
                .then()
                .spec(ok200)
                .body("ok", equalTo(true));

    }


    @Step("Удаление курьера с невалидным данными")
    public static void  deleteValidCourier(String courierId, int expectedCode, String expectedMessage){

        given().log().all()
                .spec(sendHeader)
                .when()
                .delete(url + apiV1Courier + courierId)
                .then()
                .body("code", equalTo(expectedCode))
                .body("message", equalTo(expectedMessage));
    }
}
