import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static config.Configuration.*;
import static steps.AuthSteps.invalidAuth;
import static steps.AuthSteps.validAuth;
import static steps.DeleteCourierSteps.deleteValidCourier;
import static steps.RegistrationCourierSteps.registerValidCourier;

public class LoginCourierTest {
    @Before
    public void registrationData() {
        courierLogin = "666795";
        password = "1234";
        firstName = "КапитанГав";
    }
    @Test
    @DisplayName("Успешная авторизация курьера")
    public void validAuthorization(){
        registerValidCourier(courierLogin, password, firstName);
        validAuth(courierLogin, password);

    }
    @Test
    @DisplayName("Авторизация без логина")
    public void authorizationWithoutLogin(){
        invalidAuth("","1234", 400, "Недостаточно данных для входа");
    }
    @Test
    @DisplayName("Авторизация без пароля")
    public void authorizationWithoutPassword(){
        invalidAuth("ninja","",400,"Недостаточно данных для входа");
    }

    @Test
    @DisplayName("Авторизация с неверным логином/паролем")
    public void authorizationInvalidPassword(){
        invalidAuth("OGBuda","Kashel420", 404, "Учетная запись не найдена");
    }


    @After
    public void deleteCourier(){
        if (courierId != null) {
            deleteValidCourier(courierId);
        } else {
            deleteValidCourier(null,500, "invalid input syntax for type integer: \"null\"" );
        }
    }
}
