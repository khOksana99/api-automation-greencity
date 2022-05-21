package com.greencity.auto.steps.econews;

import com.greencity.auto.constans.EndPoint;
import com.greencity.auto.pojo.EcoNews;
import com.greencity.auto.steps.common.BaseSteps;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class EcoNewsSteps extends BaseSteps<EcoNewsSteps> {
    private final EcoNewsAssertion assertion = new EcoNewsAssertion(this);

    public EcoNewsSteps() {
        super();
    }

    /**
     * A method used to begin assertion
     *
     * @return EcoNewsAssertion object
     */
    @Override
    public EcoNewsAssertion assertThat() {
        return assertion;
    }

    @Step("Get news by id {id}")
    public <T> EcoNewsSteps getNewsById(T id) {
        response = given()
                .when()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.ECONEWS.getEndpoint() + "/" + id)
                .then().log().all()
                .extract().response();
        return this;
    }


    @Step("Get all news")
    public <T> EcoNewsSteps getAllNews() {
        response =
                given()
                        .spec(getRequestSpec())
                        .when()
                        .queryParams("size", "50")
                        .get(EndPoint.ECONEWS.getEndpoint())
                        .then().log().all()
                        .extract().response();
        return this;
    }

    @Step("Get news created by current user")
    public <T> EcoNewsSteps getNewsCreatedByCurrentUser() {
        response = given()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.ECONEWS_CURRENT_USER.getEndpoint()).then().log().all()
                .extract().response();
        return this;
    }

    @Step("Edit news")
    public <T> EcoNewsSteps editNews(EcoNews ecoNews) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .body(ecoNews)
                .put(EndPoint.EDIT_NEWS.getEndpoint()).then().log().all()
                .extract().response();
        return this;
    }

    @Step("Delete news by id{id}")
    public <T> EcoNewsSteps deleteNews(T id) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .delete(EndPoint.ECONEWS.getEndpoint() + id).then().log().all()
                .extract().response();
        return this;
    }

    @Step("Like news")
    public <T> EcoNewsSteps likeNews(T id) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .queryParam("id", id)
                .post(EndPoint.LIKE_ECONEWS.getEndpoint()).then().log().all()
                .extract().response();
        return this;
    }

    @Step("Like news")
    public <T> EcoNewsSteps isLikedByUser(T id) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .queryParams("econewsId", id)
                .get(EndPoint.IS_LIKED_BY_USER.getEndpoint()).then().log().all()
                .extract().response();
        return this;
    }

    @Step("Like count")
    public <T> EcoNewsSteps likesCount(T id) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.COUNT_LIKES.getEndpoint() + id)
                .then().log().all()
                .extract().response();
        return this;
    }

    @Step("Get three last eco news")
    public <T> EcoNewsSteps getThreeLastEcoNews() {
        response = given()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.GET_THREE_LATEST_NEWS.getEndpoint()).then().log().all()
                .extract().response();
        return this;
    }

    @Step("Get three last eco news")
    public <T> EcoNewsSteps getNewsByTag(String tag) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .queryParams("tags", tag)
                .get(EndPoint.GET_NEWS_BY_TAG.getEndpoint()).then().log().all()
                .extract().response();
        return this;
    }

    @Step("Get all tags")
    private <T> EcoNewsSteps getAllNewsTags() {
        response = given()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.GET_ALL_TAGS.getEndpoint()).then().log().all()
                .extract().response();
        return this;
    }

    public int getLikesCount() {
        return Integer.parseInt(response.then().extract().asString());
    }
}
