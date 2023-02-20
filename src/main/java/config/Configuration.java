package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class Configuration {
    public static String url = "http://qa-scooter.praktikum-services.ru/";
    public static String orderId;
    public static String trackId;
    public static String courierId;
    public static String invalidOrderId = "0";
    public static String invalidTrackId = "0";
    public static String invalidCourierId = "0";
    public static String courierLogin;
    public static String password;
    public static String firstName;

    public static RequestSpecification sendHeader = given()
            .filters(new AllureRestAssured(), new ResponseLoggingFilter())
            .contentType(ContentType.JSON).accept(ContentType.JSON);

    public static final ResponseSpecification ok200 = expect()
            .statusCode(200)
            .statusLine("HTTP/1.1 200 OK");
    public static final ResponseSpecification ok201 = expect()
            .statusCode(201)
            .statusLine("HTTP/1.1 201 Created");
}
