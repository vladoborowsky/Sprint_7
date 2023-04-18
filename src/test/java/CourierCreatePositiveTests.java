import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CourierCreatePositiveTests {
    Courier courier;
    ValidatableResponse createResponse;

    @Before
    public void setUp() {
        courier = CourierGenerator.randomCourier();
        createResponse = CourierClient.create(courier);
    }

    @Test
    @DisplayName("Создание курьера")
    public void createCourier() {
        createResponse
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @After
    public void tearDown() {
        ValidatableResponse loginResponse = CourierClient.login(courier);
        int courierId = loginResponse.extract().path("id");
        CourierClient.delete(courierId);
    }
}
