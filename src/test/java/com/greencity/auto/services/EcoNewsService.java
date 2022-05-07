package com.greencity.auto.services;

import com.greencity.auto.constans.EndPoint;
import com.greencity.auto.pojo.EcoNews;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class EcoNewsService extends BaseService {
    @Step("Get all news")
    public static Response getAllNews() {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .queryParams("size", "50")
                .get(EndPoint.ECONEWS.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Get news by id")
    public static Response getNewsById(int id) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .get(EndPoint.ECONEWS.getEndpoint() + "/" + id)
                .then()
                .extract()
                .response();
    }

    @Step("Get news created by current user")
    public static Response getNewsCreatedByCurrentUser() {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .get(EndPoint.ECONEWS_CURRENT_USER.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Edit news")
    public static Response editNews(EcoNews ecoNews) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .body(ecoNews)
                .put(EndPoint.EDIT_NEWS.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Delete news")
    public static Response deleteNews(int id) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .delete(EndPoint.ECONEWS.getEndpoint() + id)
                .then()
                .extract()
                .response();
    }

    @Step("Like news")
    public static Response likeNews(int id) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .queryParam("id", id)
                .post(EndPoint.LIKE_ECONEWS.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Like news")
    public static Response isLikedByUser(int id) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .queryParams("econewsId", id)
                .get(EndPoint.IS_LIKED_BY_USER.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Like count")
    public static Response likesCount(int id) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .get(EndPoint.COUNT_LIKES.getEndpoint() + id)
                .then()
                .extract()
                .response();
    }

    @Step("Get three last eco news")
    public static Response getThreeLastEcoNews() {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .get(EndPoint.GET_THREE_LATEST_NEWS.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Get three last eco news")
    public static Response getNewsByTag(String tag) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .queryParams("tags", tag)
                .get(EndPoint.GET_NEWS_BY_TAG.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Get all tags")
    private static Response getAllNewsTags() {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .get(EndPoint.GET_ALL_TAGS.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Get list of eco news")
    public static List<EcoNews> getEconewsList() {
        Response response = getAllNews();
        response.then().assertThat().statusCode(SC_OK);
        return response.jsonPath().getList("page");
    }

    @Step("Get id of last eco news")
    public static Integer getLastEcoNewsId() {
        Response response = getAllNews();
        response.then().assertThat().statusCode(SC_OK);
        return response.jsonPath().getInt("page[0].id");
    }

    @Step("Get first tag")
    public static String getFirstTag() {
        return getAllNewsTags().jsonPath().getString("[0].name");
    }
}
