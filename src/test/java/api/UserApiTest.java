package api;

import io.qameta.allure.junit4.DisplayName;
import models.User;
import models.UserClient;
import models.UserCredentials;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UserApiTest extends BaseApiTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = generateUniqueUser();
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    public void createUniqueUserSuccess() {
        userClient.create(user)
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Создание уже зарегистрированного пользователя")
    public void createExistingUserFail() {
        userClient.create(user).statusCode(200);

        userClient.create(user)
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Создание пользователя без email")
    public void createUserWithoutEmailFail() {
        User userWithoutEmail = new User(null, "password123", "TestUser");

        userClient.create(userWithoutEmail)
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создание пользователя без password")
    public void createUserWithoutPasswordFail() {
        User userWithoutPassword = new User(generateUniqueEmail(), null, "TestUser");

        userClient.create(userWithoutPassword)
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создание пользователя без name")
    public void createUserWithoutNameFail() {
        User userWithoutName = new User(generateUniqueEmail(), "password123", null);

        userClient.create(userWithoutName)
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Логин существующего пользователя")
    public void loginExistingUserSuccess() {
        userClient.create(user).statusCode(200);

        userClient.login(UserCredentials.fromUser(user))
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()));
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    public void loginWithWrongPasswordFail() {
        userClient.create(user).statusCode(200);

        UserCredentials wrongCredentials = new UserCredentials(user.getEmail(), "wrongpassword");

        userClient.login(wrongCredentials)
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Логин с неверным логином")
    public void loginWithWrongLoginFail() {
        userClient.create(user).statusCode(200);

        UserCredentials wrongCredentials = new UserCredentials("wrong_" + user.getEmail(), user.getPassword());

        userClient.login(wrongCredentials)
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
