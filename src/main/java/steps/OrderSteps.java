package steps;

import config.Configuration;
import model.OrderHelp;

import java.util.List;
import io.qameta.allure.Step;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;




public class OrderSteps extends Configuration{
    final static String apiV1Orders = "api/v1/orders";

    @Step("Создание заказа c черным цветом самоката")
    public static void createOrderWithBlackColor() {

        OrderHelp orderBody = new OrderHelp();
       orderBody.Order("Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,
               4 ,"02-02-2023" ,"Тут должен быть комментарий" , List.of("Black"));

        given().log().all()
                .spec(sendHeader)
                .when()
                .post(url + apiV1Orders)
                .then()
                .spec(ok201)
                .body("track", notNullValue());
    }
    @Step("Создание заказа c серым цвета самоката")
    public static void createOrderWithGrayColor() {

        OrderHelp orderBody = new OrderHelp();
        orderBody.Order("Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,
                4 ,"02-02-2023" ,"Тут должен быть комментарий" , List.of("Gray"));

        given().log().all()
                .spec(sendHeader)
                .when()
                .post(url + apiV1Orders)
                .then()
                .spec(ok201)
                .body("track", notNullValue());
    }
    @Step("Создание заказа c 2мя цветами самоката")
    public static void createOrderWithBothColor() {

        OrderHelp orderBody = new OrderHelp();
        orderBody.Order("Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,
                4 ,"02-02-2023" ,"Тут должен быть комментарий" , List.of("Black", "Gray"));

        given().log().all()
                .spec(sendHeader)
                .when()
                .post(url + apiV1Orders)
                .then()
                .spec(ok201)
                .body("track", notNullValue());
    }
    @Step("Создание заказа без выбора цвета самоката")
    public static void createOrderWithoutColor() {

        OrderHelp orderBody = new OrderHelp();
        orderBody.Order("Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,4 ,"02-02-2023" ,"Тут должен быть комментарий" , null);

        given().log().all()
                .spec(sendHeader)
                .when()
                .post(url + apiV1Orders)
                .then()
                .spec(ok201)
                .body("track", notNullValue());
    }

    @Step("Получение списка всех заказов")
    public static void getOrders() {

        given().log().all()
                .spec(sendHeader)
                .when()
                .get(url + apiV1Orders)
                .then()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath("getOrder.json"));
    }

}
