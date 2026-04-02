package models;

import api.BaseApiTest;           // ← импорт из api
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import static io.restassured.RestAssured.given;

public class OrderClient extends BaseApiTest {
    private final String ORDERS_PATH = "/api/orders";
    private final String INGREDIENTS_PATH = "/api/ingredients";

    @Step("Создание заказа с авторизацией")
    public ValidatableResponse createWithAuth(Order order, String token) {
        return given()
                .spec(getAuthSpec(token))
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Создание заказа без авторизации")
    public ValidatableResponse createWithoutAuth(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Получение списка id ингредиентов")
    public List<String> getIngredientIds() {
        return given()
                .spec(getBaseSpec())
                .get(INGREDIENTS_PATH)
                .then()
                .statusCode(200)
                .extract()
                .path("data._id");
    }
}