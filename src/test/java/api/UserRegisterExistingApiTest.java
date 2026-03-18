package api;

import io.qameta.allure.junit4.DisplayName;
import models.User;
import models.UserClient;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class UserRegisterExistingApiTest extends BaseApiTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = generateUniqueUser();
        userClient.create(user).statusCode(200);
    }

    @Test
    @DisplayName("Создание уже зарегистрированного пользователя")
    public void createExistingUserFail() {
        userClient.create(user)
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo("User already exists"));
    }
}

