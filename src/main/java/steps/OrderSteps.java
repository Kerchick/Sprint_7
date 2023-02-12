package steps;

import config.Configuration;
import model.OrderHelp;

import java.util.List;
import io.qameta.allure.Step;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;




public class OrderSteps extends Configuration{

    @Step("Создание заказа")
    public static void createOrder() {

        OrderHelp orderBody = new OrderHelp();
       orderBody.Order("Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,4 ,"02-02-2023" ,"Тут должен быть комментарий" ,List.of("Black"));

       trackId = given().log().all()
                .spec(sendHeader)
                .when()
                .post(url+"api/v1/orders")
                .then()
                .spec(ok201)
                .body("track", notNullValue())
                .extract().jsonPath().getString("track");
    }


    @Step("Получение списка всех заказов")
    public static void getOrders() {

        given().log().all()
                .spec(sendHeader)
                .when()
                .get(url+"api/v1/orders")
                .then()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath("getOrder.json"));
    }

}
