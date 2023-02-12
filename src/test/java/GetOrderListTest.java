import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static steps.OrderSteps.getOrders;

public class GetOrderListTest {

    @Test
    @DisplayName("Получение списка всех заказов")
    public void getOrderListTest(){
        getOrders();
    }
}
