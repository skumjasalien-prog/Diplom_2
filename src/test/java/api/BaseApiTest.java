package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.User;
import java.util.Random;

public class BaseApiTest {
    protected final String BASE_URL = "https://stellarburgers.education-services.ru";
    protected final Random random = new Random();

    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

    protected RequestSpecification getAuthSpec(String token) {
        return getBaseSpec()
                .header("Authorization", token);
    }

    protected String generateUniqueEmail() {
        return "testuser" + System.currentTimeMillis() + "@yandex.ru";
    }

    protected String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    protected User generateUniqueUser() {
        return new User(
                generateUniqueEmail(),
                generateRandomString(8),
                generateRandomString(6)
        );
    }
}