package steps;

import config.Configuration;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class DeleteCourierSteps extends Configuration {

    @Step("Удаление курьера с валидным данными")
    public static void deleteValidCourier(String courierId) {

        given().log().all()
                .spec(sendHeader)
                .when()
                .delete(url + "api/v1/courier/" + courierId)
                .then()
                .spec(ok200)
                .body("ok", equalTo(true));

    }


    @Step("Удаление курьера с невалидным данными")
    public static void  deleteInvalidCourier(String courierId, int expectedCode, String expectedMessage){

        given().log().all()
                .spec(sendHeader)
                .when()
                .delete(url + "api/v1/courier/" + courierId)
                .then()
                .body("code", equalTo(expectedCode))
                .body("message", equalTo(expectedMessage));
    }
}
