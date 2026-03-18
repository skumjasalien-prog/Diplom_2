package api;

import io.qameta.allure.junit4.DisplayName;
import models.User;
import models.UserClient;
import models.UserCredentials;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class UserLoginApiTest extends BaseApiTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = generateUniqueUser();
        userClient.create(user).statusCode(200);
    }

    @Test
    @DisplayName("Логин существующего пользователя")
    public void loginExistingUserSuccess() {
        userClient.login(UserCredentials.fromUser(user))
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()));
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    public void loginWithWrongPasswordFail() {
        UserCredentials wrongCredentials = new UserCredentials(user.getEmail(), "wrongpassword");

        userClient.login(wrongCredentials)
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Логин с неверным логином")
    public void loginWithWrongLoginFail() {
        UserCredentials wrongCredentials = new UserCredentials("wrong_" + user.getEmail(), user.getPassword());

        userClient.login(wrongCredentials)
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}

