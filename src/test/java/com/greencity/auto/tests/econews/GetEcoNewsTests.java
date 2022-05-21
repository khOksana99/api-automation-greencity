package com.greencity.auto.tests.econews;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

@Feature("Get eco news")
public class GetEcoNewsTests extends BaseEconewsTest {
    private final Integer CREATED_BY_USER_NEWS = 61;
    private final String tag = "NEWS";

    @Test(description = "Get all eco news")
    public void getAllEcoNewsTest() {
        ecoNews
                .getAllNews()
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .getAllEcoNewsSchemaIsCorrect();
    }

    @Test(description = "Get eco news by id")
    public void getEcoNewsByIdTest() {
       ecoNews
               .getNewsById(CREATED_BY_USER_NEWS)
               .assertThat()
               .statusCodeIs(SC_OK);
    }

    @Test(description = "Get eco news created by current user1")
    public void getEcoNewsCreatedByCurrentUser() {
        ecoNews
                .getNewsCreatedByCurrentUser()
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .verifyEcoNEwsAreCreatedByUser();
    }

    @Test(description = "Get news by tag")
    public void verifyGetEcoNewsByTag() {
        ecoNews
                .getNewsByTag(tag)
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .verifyTags(tag);
    }
}
