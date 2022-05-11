package com.greencity.auto.services;

import com.greencity.auto.authorization.Authenticator;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.greencity.auto.authorization.Authenticator.getAuthToken;
import static com.greencity.auto.utils.PropertyUtil.getPropertyValueByKey;

public class BaseService {
    protected static final String BASE_URI = getPropertyValueByKey("baseUrl");

    @Step("Create basic request")
    protected static RequestSpecification getRequestSpec(String uri) {
        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .addHeader("Authorization","Bearer " + Authenticator.getAuthToken())
                .addHeader("Host", "greencity.azurewebsites.net")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundarydxsi6EEVZMtrnlhj")
                .addHeader("Accept", "application/json, text/plain, */*")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }
}
