import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static config.Configuration.*;
import static steps.AuthSteps.validAuth;
import static steps.DeleteCourierSteps.deleteValidCourier;
import static steps.RegistrationCourierSteps.registerInvalidCourier;
import static steps.RegistrationCourierSteps.registerValidCourier;

public class RegisterCourierTest {
    @Before
    public void registrationData() {
        courierLogin = "666769";
        password = "1234";
        firstName = "КапитанГав";
    }

    @Test
    @DisplayName("Создание курьера с валидными данными")
    public void createCourierWithValidData(){
        registerValidCourier(courierLogin, password, firstName);
        validAuth(courierLogin, password);
        deleteValidCourier(courierId);
    }
    @Test
    @DisplayName("Создание курьера с одинаковым логином")
    public void createCourierWithSameLogin(){
        registerValidCourier(courierLogin, password, firstName);
        registerInvalidCourier(courierLogin, password, firstName, 409, "Этот логин уже используется. Попробуйте другой.");
        validAuth(courierLogin,password);
        deleteValidCourier(courierId);
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void createCourierWithoutLogin(){
        registerInvalidCourier("", password, firstName,400, "Недостаточно данных для создания учетной записи");
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierWithoutPassword(){
        registerInvalidCourier(courierLogin,"", firstName, 400, "Недостаточно данных для создания учетной записи");
    }

    @Test
    @DisplayName("Создание курьера без имени")
    public void createCourierWithoutName(){
        registerValidCourier(courierLogin, password, "");
        validAuth(courierLogin, password);
        deleteValidCourier(courierId);
    }
}