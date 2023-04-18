import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CourierCreateNegativeTests {
    Courier courier;

    @Before
    public void setUp() {
        courier = CourierGenerator.randomCourier();
    }

    @Test
    @DisplayName("Пробуем создать курьера не передав логин")
    public void createCourierWithoutLogin() {
        CourierClient.create(courier, "login")
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Пробуем создать курьера не передав пароль")
    public void createCourierWithoutPassword() {
        CourierClient.create(courier, "password")
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Пробуем создать двух одинаковых курьеров")
    public void createIdenticalCourier() {
        CourierClient.create(courier)
                .statusCode(201)
                .body("ok", equalTo(true));

        CourierClient.create(courier)
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        ValidatableResponse loginResponse = CourierClient.login(courier);
        int courierId = loginResponse.extract().path("id");
        CourierClient.delete(courierId);
    }
}
