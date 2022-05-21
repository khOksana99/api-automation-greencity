package com.greencity.auto.tests.econews;

import com.greencity.auto.tests.baseTests.BaseEconewsTest;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

@Feature("Like eco news")
public class LikeEcoNewsTests extends BaseEconewsTest {

    @Test(description = "Like eco news", priority = 1)
    public void verifyLikeEcoNewsTest() {
        ecoNews
                .likeNews(CREATED_BY_USER_NEWS_ID)
                .assertThat()
                .statusCodeIs(SC_OK);

        ecoNews
                .isLikedByUser(CREATED_BY_USER_NEWS_ID)
                .assertThat()
                .verifyIsLikedByUser();
    }

    @Test(description = "Unlike eco news", priority = 2)
    public void verifyUnlikeEcoNewsTest() {
        ecoNews
                .likeNews(CREATED_BY_USER_NEWS_ID)
                .assertThat()
                .statusCodeIs(SC_OK);

        ecoNews
                .isLikedByUser(CREATED_BY_USER_NEWS_ID)
                .assertThat()
                .verifyIsNotLikedByUser();
    }
}
