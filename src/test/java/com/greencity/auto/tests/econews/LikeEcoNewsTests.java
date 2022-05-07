package com.greencity.auto.tests.econews;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static com.greencity.auto.services.EcoNewsService.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("Like eco news")
public class LikeEcoNewsTests {
    private final Integer CREATED_BY_USER_NEWS_ID = 61;
    private Integer startLikeCount;
    private int endLikeCount;

    @Test(description = "Like eco news", priority = 1)
    public void verifyLikeEcoNewsTest() {
        startLikeCount = Integer.parseInt(likesCount(CREATED_BY_USER_NEWS_ID).then().extract().asString());

        likeNews(CREATED_BY_USER_NEWS_ID)
                .then()
                .assertThat()
                .statusCode(SC_OK);

        String res = isLikedByUser(CREATED_BY_USER_NEWS_ID)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .asString();

        assertEquals(res, "true");

        endLikeCount = Integer.parseInt(likesCount(CREATED_BY_USER_NEWS_ID).then().extract().asString());

        assertTrue(startLikeCount < endLikeCount);
    }

    @Test(description = "Unlike eco news", priority = 2)
    public void verifyUnlikeEcoNewsTest() {
        startLikeCount = Integer.parseInt(likesCount(CREATED_BY_USER_NEWS_ID).then().extract().asString());

        likeNews(CREATED_BY_USER_NEWS_ID)
                .then()
                .assertThat()
                .statusCode(SC_OK);

        String res = isLikedByUser(CREATED_BY_USER_NEWS_ID)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .asString();

        assertEquals(res, "false");

        endLikeCount = Integer.parseInt(likesCount(CREATED_BY_USER_NEWS_ID).then().extract().asString());

        assertTrue(startLikeCount > endLikeCount);
    }
}
