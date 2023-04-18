import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Step("Получение списка заказов")
    public static ValidatableResponse getList() {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body("")
                .get("/api/v1/orders" )
                .then();
    }
    @Step("Создание заказа")
    public static ValidatableResponse create(Order order) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(order)
                .post("/api/v1/orders" )
                .then();
    }

}
