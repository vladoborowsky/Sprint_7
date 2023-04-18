import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class CourierLoginTests {
    Courier courier;
    ValidatableResponse createResponse;

    @Before
    public void setUp() {
        courier = CourierGenerator.randomCourier();
        createResponse = CourierClient.create(courier);
    }

    @Test
    @DisplayName("Курьер может авторизоваться, успешный запрос возвращает id")
    public void loginCourier() {
        CourierClient.login(courier)
                .statusCode(200)
                .body("id", is(notNullValue()));
    }

    @Test
    @DisplayName("Для авторизации нужно передать логин, иначе запрос возвращает ошибку")
    public void loginCourierWithoutLogin() {
        CourierClient.login(courier, "login")
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Для авторизации нужно передать пароль, иначе запрос возвращает ошибку")
    public void loginCourierWithoutPassword() {
        CourierClient.login(courier, "password")
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Cистема вернёт ошибку, если неправильно указать логин или пароль, если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void loginCourierWithIncorrectFields() {
        Courier randomCourier = CourierGenerator.randomCourier();
        ValidatableResponse loginResponse = CourierClient.login(randomCourier)
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        ValidatableResponse loginResponse = CourierClient.login(courier);
        int courierId = loginResponse.extract().path("id");
        CourierClient.delete(courierId);
    }
}
