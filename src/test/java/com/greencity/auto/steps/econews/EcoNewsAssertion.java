package com.greencity.auto.steps.econews;

import com.greencity.auto.authorization.AuthenticationData;
import com.greencity.auto.steps.common.CommonAssertion;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

/**
 * EcoNewsAssertion class is the class for the steps for Eco news assertion, containing
 * common methods and fields.
 */
public class EcoNewsAssertion extends CommonAssertion<EcoNewsSteps> {

    public EcoNewsAssertion(EcoNewsSteps caller) {
        super(caller);
    }

    /**
     * Verifies json schema from get all econews request
     * @return econews steps
     */
    @Step("Verify json schema for all eco news")
    public EcoNewsSteps getAllEcoNewsSchemaIsCorrect() {
        checkJsonSchema("getAllNews.json");
        return steps;
    }

    /**
     * Verifies eco news is created by user
     * @return econews steps
     */
    @Step("Verify eco news is created by user")
    public EcoNewsSteps verifyEcoNewsAreCreatedByUser() {
        getListFromResponse("author.name").stream()
                .forEach(
                        user_name -> assertThat(user_name, equalTo(new AuthenticationData().getNikName())));
        return steps;
    }

    /**
     * Verifies there is at least one news with provided tag
     * @param paramTag tag
     * @return EcoNewsSteps
     */
    @Step("Verify there is at least one news with provided tag")
    public EcoNewsSteps verifyTags(String paramTag) {
        getListFromResponse("page.tags[0]").stream()
                        .forEach(tag -> assertThat(tag.toLowerCase(Locale.ROOT), equalTo(paramTag.toLowerCase(Locale.ROOT))));
        return steps;
    }

    /**
     * Verifies eco news is liked by user
     * @return EcoNewsSteps
     */
    @Step("Verify eco news is liked by user")
    public EcoNewsSteps verifyIsLikedByUser() {
       assertTrue(steps.getResponse().then()
                .extract()
                .asString().equalsIgnoreCase("true"));
        return steps;
    }

    /**
     * Verifies eco news is not liked by user
     * @return EcoNewsSteps
     */
    @Step("Verify eco news is not liked by user")
    public EcoNewsSteps verifyIsNotLikedByUser() {
        assertTrue(steps.getResponse().then()
                .extract()
                .asString().equalsIgnoreCase("false"));
        return steps;
    }

    private List<String> getListFromResponse(String path) {
        return steps
                .getResponse()
                .jsonPath()
                .getList(path);
    }
}
