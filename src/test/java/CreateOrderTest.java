import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static config.Configuration.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public List<String> color;

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime,
                                             String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {1}, {2}, {3}")
    public static Object[][] getColor() {
        return new Object[][]{
                {"Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,4 ,"02-02-2023" ,"Тут должен быть комментарий" ,
                        List.of("Black")},
                {"Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,4 ,"02-02-2023" ,"Тут должен быть комментарий" ,
                        List.of("Gray")},
                {"Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,4 ,"02-02-2023" ,"Тут должен быть комментарий" ,
                        List.of("Black", "Gray")},
                {"Кирилл","Покемонов" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,4 ,"02-02-2023" ,"Тут должен быть комментарий" ,
                       null},
                {"Kirill","Pokemonov" ,"Ул Пушкина" ,"Алтуфьево" ,"9858885458" ,4 ,"02-02-2023" ,"Тут должен быть комментарий" ,
                        null},
        };
    }

    @Test
    @DisplayName("Создание заказов с разными данными ")
    public void createOrderTest() {
        CreateOrderTest order = new CreateOrderTest(firstName, lastName, address, metroStation,
                phone, rentTime, deliveryDate, comment, color);
        given().log().all()
                .spec(sendHeader)
                .body(order)
                .when()
                .post(url + "api/v1/orders/")
                .then()
                .body("track", notNullValue());
    }
}