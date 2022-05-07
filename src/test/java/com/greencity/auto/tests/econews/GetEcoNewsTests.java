package com.greencity.auto.tests.econews;

import com.greencity.auto.authorization.AuthenticationData;
import com.greencity.auto.pojo.EcoNews;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.greencity.auto.services.EcoNewsService.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Feature("Get eco news")
public class GetEcoNewsTests {
    private final Integer CRESTED_BY_USER_NEWS_ID = 61;
    private String tag;

    @BeforeClass
    public void setUp() {
        tag = getFirstTag();
    }

    @Test(description = "Get all eco news")
    public void getAllEcoNewsTest() {
        Response response = getAllNews();

        response.then()
                .assertThat()
                .statusCode(SC_OK);
        List<EcoNews> list = getEconewsList();
        assertThat(list.size(), is(response.jsonPath().getInt("totalElements")));
    }

    @Test(description = "Get eco news by id")
    public void getEcoNewsByIdTest() {
        getNewsById(CRESTED_BY_USER_NEWS_ID)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .body("id", is(CRESTED_BY_USER_NEWS_ID));
    }

    @Test(description = "Get eco news created by current user1")
    public void getEcoNewsCreatedByCurrentUser() {
        Response response = getNewsCreatedByCurrentUser();
        response
                .then()
                .assertThat()
                .statusCode(SC_OK);

        response.jsonPath().getList("author.name").forEach(
                user_name -> assertThat(user_name, equalTo(new AuthenticationData().getUserName()))
        );
    }

    @Test(description = "Get news by tag")
    public void verifyGetEcoNewsByTag() {
        Response response = getNewsByTag(tag);

        response
                .then()
                .assertThat()
                .statusCode(SC_OK);

        response.jsonPath().getList("page.tags").forEach(
                tag -> assertThat(tag, equalTo(tag))
        );
    }
}
