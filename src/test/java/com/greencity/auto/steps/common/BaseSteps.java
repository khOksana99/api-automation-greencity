package com.greencity.auto.steps.common;

import com.greencity.auto.authorization.Authenticator;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import static com.greencity.auto.utils.PropertyUtil.getPropertyValueByKey;

/**
 * BaseSteps class is the base class for all the Steps classes, containing
 * common methods and fields.
 */
public class BaseSteps<T extends BaseSteps<T>> {
    protected static final String BASE_URI = getPropertyValueByKey("baseUrl");
    @Getter
    protected Response response;

    /**
     * A method used to begin assertion
     *
     * @return CommonAssertion object
     */
    public CommonAssertion<T> assertThat() {
        return new CommonAssertion<>((T)this);
    }


    @Step("Create basic request")
    protected RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
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


