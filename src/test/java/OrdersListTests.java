import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrdersListTests {

    @Test
    @DisplayName("Проверка, что в тело ответа возвращается список заказов")
    public void getList() {
        ValidatableResponse orderResponse = OrderClient.getList();
        OrderList orderList = orderResponse.extract().body().as(OrderList.class);

        assertEquals(200, orderResponse.extract().statusCode());
        assertNotNull(orderList);
    }
}