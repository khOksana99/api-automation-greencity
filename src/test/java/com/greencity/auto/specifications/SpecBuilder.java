package com.greencity.auto.specifications;

import com.greencity.auto.authorization.Authenticator;
import com.greencity.auto.utils.Util;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j;

import static com.greencity.auto.utils.PropertyUtil.getPropertyValueByKey;

/**
 * SpecBuilder class stands for creating and configuring RestAssured request specification
 */
public class SpecBuilder {
    public static final int HTTP_SOCKET_TIMEOUT = 30000;
    protected static final String BASE_URI = getPropertyValueByKey("baseUrl");

    private static SpecBuilder instance;

//    static {
//        getLogLevel();
//    }

    private int requestCount = 0;
    private final String sessionToken;

    private SpecBuilder() {
        sessionToken = Util.getCurrentDateTime();
    }

    /**
     * A method to return the only instance of this
     *
     * @return SpecBuilder object
     */
    public static SpecBuilder getInstance() {
        if (instance == null) {
            instance = new SpecBuilder();
        }
        return instance;
    }

    /**
     * Builds request with the properties needed for all the requests done by
     * test classes:
     * 'http.socket.timeout' config parameter,
     * 'x-auto-api-test-session' and 'x-auto-api-test-request' headers,
     * AllureRestAssured filter
     *
     * @return RequestSpecBuilder object with the properties and headers set
     */
    public RequestSpecBuilder baseSpecBuild() {
        requestCount++;
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader("Authorization","Bearer " + Authenticator.getAuthToken())
                .addHeader("Host", "greencity.azurewebsites.net")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundarydxsi6EEVZMtrnlhj")
                .addHeader("Accept", "application/json, text/plain, */*")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON);
    }

//    /**
//     * This method sets RestAssured LogDetail level based on log4j log level
//     */
//    private static void setLogLevel() {
//        if (log.isTraceEnabled()) {
//            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//        } else if (log.isDebugEnabled()) {
//            RestAssured.filters(new RequestLoggingFilter(),
//                    new ResponseLoggingFilter(LogDetail.STATUS));
//        } else if (log.isInfoEnabled()) {
//            RestAssured.filters(new RequestLoggingFilter(LogDetail.URI),
//                    new ResponseLoggingFilter(LogDetail.STATUS));
//        } else if (log.isWarnEnabled()) {
//            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        }
//    }

    /**
     * This method builds a request with the properties needed for all the
     * requests done by test classes
     *
     * @retur RequestSpecification to use in test methods
     */
    public RequestSpecification baseSpec() {
        return baseSpecBuild()
                .setBaseUri(BASE_URI)
                .build();
    }
}
