package com.greencity.auto.steps.common;

import com.greencity.auto.steps.econews.EcoNewsCommentSteps;
import io.qameta.allure.Step;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommonAssertion<T extends BaseSteps<T>> {
    protected final T steps;

    public CommonAssertion(T steps) {
        this.steps = steps;
    }

    /**
     * Verifies response code equals expected
     *
     * @param statusCode expected status code
     *
     * @return T this
     */
    @Step("Status code should be: {statusCode}")
    public T statusCodeIs(int statusCode) {
        steps.getResponse().then().assertThat().statusCode(statusCode);
        return steps;
    }

    /**
     * Verifies response matches JSON schema
     *
     * @param fileName the name of JSON schema file
     *
     * @return T this
     */
    @Step("Validate json schema")
    public T checkJsonSchema(String fileName) {
        steps.getResponse().then().assertThat().body(matchesJsonSchemaInClasspath("schemas/" + fileName));
        return steps;
    }

    /**
     * Verifies response body error.title equals expected
     *
     * @param textOfTitle string to verify against
     *
     * @return T this
     */
    @Step("Check title of error message: {textOfTitle}")
    public T checkTitleOfErrorMessage(String textOfTitle) {
        steps.getResponse().then().assertThat().body("error.title", equalTo(textOfTitle));
        //noinspection unchecked
        return steps;
    }

    /**
     * Verifies response body error.title equals expected
     *
     * @param textOfTitle   string to verify against
     * @param id            to format textOfTitle with
     *
     * @return T this
     */
    @Step("Check title of error message: {textOfTitle} by {id}")
    public T checkTitleOfErrorMessage(String textOfTitle, int id) {
        return checkTitleOfErrorMessage(String.format(textOfTitle, id));
    }

    public T verifyTextChanged(String startText, String endText) {
        assertThat(startText, equalTo(endText));
        return steps;
    }
}
