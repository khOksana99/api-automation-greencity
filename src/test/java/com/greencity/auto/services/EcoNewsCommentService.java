package com.greencity.auto.services;

import com.greencity.auto.constans.EndPoint;
import com.greencity.auto.pojo.CommentEcoNews;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;

public class EcoNewsCommentService extends BaseService {
    @Step("Comment eco news")
    public static Response commentEcoNews(int id, String text) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .body(CommentEcoNews.builder().text(text).build())
                .post(EndPoint.COMMENT_NEWS.getEndpoint() + id)
                .then()
                .extract()
                .response();
    }

    @Step("Edit comment eco news")
    public static Response editCommentEcoNews(int commentId, int newsId, String text) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .body(CommentEcoNews.builder().parentCommentId(commentId).text(text).build())
                .patch(EndPoint.COMMENT_NEWS.getEndpoint() + newsId)
                .then()
                .extract()
                .response();
    }

    @Step("Delete comment eco news")
    public static Response deleteCommentEcoNews(int commentId) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .queryParams("id", commentId)
                .delete(EndPoint.COMMENT_NEWS.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Get all comments eco news")
    public static Response getAllCommentsEcoNews(int newsId) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .queryParams("ecoNewsId", newsId)
                .get(EndPoint.ACTIVE_COMMENTS_NEWS.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Count comments eco news")
    public static Response countCommentsEcoNews(int newsId) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .get(EndPoint.COUNT_COMMENTS_NEWS.getEndpoint() + newsId)
                .then()
                .extract()
                .response();
    }

    @Step("Like comment eco news")
    public static Response likeCommentEcoNews(int commentId) {
        return given()
                .spec(getRequestSpec(BASE_URI))
                .when()
                .queryParams("id", commentId)
                .post(EndPoint.LIKE_COMMENT_NEWS.getEndpoint())
                .then()
                .extract()
                .response();
    }

    @Step("Get id of last eco news")
    public static Integer getCreatedCommentId(int id, String text) {
        Response response = commentEcoNews(id, text);
        response.then().assertThat().statusCode(SC_CREATED);
        return response.jsonPath().getInt("id");
    }
}
