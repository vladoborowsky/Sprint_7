import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import com.google.gson.Gson;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    public static ValidatableResponse create(Courier courier) {
        return create(courier, null);
    }

    @Step("Создание курьера")
    public static ValidatableResponse create(Courier courier, String excludeField) {
        JsonObject jsonObject = new Gson()
                .toJsonTree(courier)
                .getAsJsonObject();

        if (excludeField != null) {
            jsonObject.remove(excludeField);
        }

        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(jsonObject)
                .post("/api/v1/courier/")
                .then();
    }

    public static ValidatableResponse login(Courier courier) {
        return login(courier, null);
    }
    @Step("Авторизация курьера")
    public static ValidatableResponse login(Courier courier, String excludeField) {
        JsonObject jsonObject = new Gson()
                .toJsonTree(courier)
                .getAsJsonObject();

        if (excludeField != null) {
            jsonObject.remove(excludeField);
        }

        jsonObject.remove("firstName");

        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(jsonObject)
                .post("/api/v1/courier/login")
                .then();
    }

    @Step("Удаление курьера")
    public static ValidatableResponse delete(int courierId) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(Integer.toString(courierId))
                .post("/api/v1/courier/" + courierId)
                .then();
    }
}
