package models;

import api.BaseApiTest;           // ← импорт из api
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserClient extends BaseApiTest {
    private final String REGISTER_PATH = "/api/auth/register";
    private final String LOGIN_PATH = "/api/auth/login";

    @Step("Создание пользователя")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then();
    }

    @Step("Логин пользователя")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    @Step("Получение accessToken для пользователя")
    public String getAccessToken(User user) {
        return create(user)
                .statusCode(200)
                .extract()
                .path("accessToken");
    }
}