package com.greencity.auto.authorization;

import com.greencity.auto.constans.EndPoint;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.greencity.auto.utils.PropertyUtil.getPropertyValueByKey;
import static io.restassured.RestAssured.given;

public class Authenticator {
    private final static String AUTH_TYPE = "Bearer ";
    private final static String BASE_URI = getPropertyValueByKey("baseUrl");
    private static final AuthenticationData authenticationData = new AuthenticationData();

    public static String getAuthToken() {
        final String TOKEN = System.getProperty("token");

        return TOKEN != null
                ? TOKEN
                : getPureAuthToken();
    }

    private static String getPureAuthToken() {
        Response response = given()
                .header("Host", "greencity-user.azurewebsites.net")
                .header("Content-Type", "application/json")
                .body(authenticationData)
                .when().log().all()
                .post(BASE_URI + EndPoint.LOGIN.getEndpoint())
                .then().log().all()
                .extract()
                .response();
        return AUTH_TYPE + response.jsonPath().getString("accessToken");
    }
}
