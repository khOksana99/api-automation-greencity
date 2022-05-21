package com.greencity.auto.steps.econews;

import com.greencity.auto.authorization.AuthenticationData;
import com.greencity.auto.steps.common.CommonAssertion;

import java.util.List;
import java.util.Locale;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EcoNewsAssertion extends CommonAssertion<EcoNewsSteps> {

    public EcoNewsAssertion(EcoNewsSteps caller) {
        super(caller);
    }

    public EcoNewsSteps getAllEcoNewsSchemaIsCorrect() {
        checkJsonSchema("getAllNews.json");
        return steps;
    }

    public EcoNewsSteps verifyEcoNEwsAreCreatedByUser() {
        getListFromResponse("author.name").stream()
                .forEach(
                        user_name -> assertThat(user_name, equalTo(new AuthenticationData().getUserName())));
        return steps;
    }

    public EcoNewsSteps verifyTags(String paramTag) {
        getListFromResponse("page.tags[0]").stream()
                        .forEach(tag -> assertThat(tag.toLowerCase(Locale.ROOT), equalTo(paramTag.toLowerCase(Locale.ROOT))));
        return steps;
    }

    public EcoNewsSteps verifyIsLikedByUser() {
       assertTrue(steps.getResponse().then()
                .extract()
                .asString().equalsIgnoreCase("true"));
        return steps;
    }

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
