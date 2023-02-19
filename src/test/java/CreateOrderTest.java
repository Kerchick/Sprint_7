import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static steps.OrderSteps.*;

public class CreateOrderTest{
    @Test
    @DisplayName("Создание заказа с черным цветом самоката")
    public void createOrderWithBlackColorTest() {
        createOrderWithBlackColor();
    }
    @Test
    @DisplayName("Создание заказа с серым цветом самоката")
    public void createOrderWithGrayColorTest() {
        createOrderWithGrayColor();
    }
    @Test
    @DisplayName("Создание заказа с двумя цветами самоката")
    public void createOrderWithBothColorTest() {
        createOrderWithBothColor();
    }
    @Test
    @DisplayName("Создание заказа без выбора цвета самоката")
    public void createOrderWithoutColorTest() {
        createOrderWithoutColor();
    }
}