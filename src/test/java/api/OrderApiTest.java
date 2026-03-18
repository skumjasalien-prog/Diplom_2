package api;

import io.qameta.allure.junit4.DisplayName;
import models.Order;
import models.OrderClient;
import models.User;
import models.UserClient;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderApiTest extends BaseApiTest {
    private OrderClient orderClient;
    private UserClient userClient;
    private User user;
    private String accessToken;
    private List<String> ingredientIds;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
        userClient = new UserClient();

        user = generateUniqueUser();
        accessToken = userClient.getAccessToken(user);
        ingredientIds = orderClient.getIngredientIds();
    }

    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void createOrderWithAuthSuccess() {
        Order order = new Order(Arrays.asList(
                ingredientIds.get(0),
                ingredientIds.get(1)
        ));

        orderClient.createWithAuth(order, accessToken)
                .statusCode(200)
                .body("success", is(true))
                .body("order.number", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void createOrderWithoutAuthSuccess() {
        Order order = new Order(Arrays.asList(
                ingredientIds.get(0)
        ));

        orderClient.createWithoutAuth(order)
                .statusCode(200)
                .body("success", is(true))
                .body("order.number", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа с ингредиентами")
    public void createOrderWithIngredientsSuccess() {
        Order order = new Order(Arrays.asList(
                ingredientIds.get(0),
                ingredientIds.get(1)
        ));

        orderClient.createWithAuth(order, accessToken)
                .statusCode(200)
                .body("success", is(true));
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredientsFail() {
        Order order = new Order(Arrays.asList());

        orderClient.createWithAuth(order, accessToken)
                .statusCode(400)
                .body("success", is(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем")
    public void createOrderWithInvalidHashFail() {
        Order order = new Order(Arrays.asList(
                "invalidhash123",
                ingredientIds.get(0)
        ));

        orderClient.createWithAuth(order, accessToken)
                .statusCode(500);
    }
}
