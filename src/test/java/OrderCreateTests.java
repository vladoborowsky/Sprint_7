import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.*;


@RunWith(Parameterized.class)
public class OrderCreateTests {
    private final List<String> color;

    public OrderCreateTests(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColorData() {
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()},
        };
    }

    @Test
    @DisplayName("Проверка создания заказа")
    public void shouldBeSum() {
        Order order = OrderGenerator.randomOrder();
        order.setColor(color);
        OrderClient.create(order)
                .statusCode(201 )
                .body("track", is(notNullValue()));
    }
}
