package com.greencity.auto.steps.econews;

import com.greencity.auto.constans.EndPoint;
import com.greencity.auto.pojo.Comment;
import com.greencity.auto.pojo.CommentEcoNews;
import com.greencity.auto.steps.common.BaseSteps;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class EcoNewsCommentSteps extends BaseSteps<EcoNewsCommentSteps> {
    private final EcoNewsCommentAssertion assertion = new EcoNewsCommentAssertion(this);

    public EcoNewsCommentSteps() {
        super();
    }

    /**
     * A method used to begin assertion
     *
     * @return EcoNewsAssertion object
     */
    @Override
    public EcoNewsCommentAssertion assertThat() {
        return assertion;
    }

    @Step("Comment eco news")
    public <T> EcoNewsCommentSteps commentEcoNews(int id, String text) {
        response = given()
                .spec(getRequestSpec())
                .when().log().all()
                .body(CommentEcoNews.builder().text(text).build())
                .post(EndPoint.COMMENT_NEWS.getEndpoint() + id)
                .then().log().all()
                .extract()
                .response();
        return this;
    }

    @Step("Edit comment eco news")
    public <T> EcoNewsCommentSteps editCommentEcoNews(int commentId, int newsId, String text) {
        response = given()
                .spec(getRequestSpec())
                .when().log().all()
                .body(CommentEcoNews.builder().parentCommentId(commentId).text(text).build())
                .patch(EndPoint.COMMENT_NEWS.getEndpoint() + newsId)
                .then().log().all()
                .extract()
                .response();
        return this;
    }

    @Step("Delete comment eco news")
    public <T> EcoNewsCommentSteps deleteCommentEcoNews(int commentId) {
        response = given()
                .spec(getRequestSpec())
                .when().log().all()
                .queryParams("id", commentId)
                .delete(EndPoint.COMMENT_NEWS.getEndpoint())
                .then().log().all()
                .extract()
                .response();
        return this;
    }

    @Step("Get all comments eco news")
    public <T> EcoNewsCommentSteps getAllCommentsEcoNews(int newsId) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .queryParams("ecoNewsId", newsId)
                .get(EndPoint.ACTIVE_COMMENTS_NEWS.getEndpoint())
                .then()
                .extract()
                .response();
        return this;
    }

    @Step("Count comments eco news")
    public <T> EcoNewsCommentSteps countCommentsEcoNews(int newsId) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.COUNT_COMMENTS_NEWS.getEndpoint() + newsId)
                .then()
                .extract()
                .response();
        return this;
    }

    @Step("Like comment eco news")
    public <T> EcoNewsCommentSteps likeCommentEcoNews(int commentId) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .queryParams("id", commentId)
                .post(EndPoint.LIKE_COMMENT_NEWS.getEndpoint())
                .then()
                .extract()
                .response();
        return this;
    }

    @Step("Get id of last eco news")
    public Integer getCreatedCommentId() {
        return response.jsonPath().getInt("id");
    }

    public String getTextOfCommentById(int newsId, int commentedId) {
       return getAllCommentsEcoNews(newsId).getResponse().jsonPath().getList("page", Comment.class)
                .stream()
                .filter(comment -> comment.getId().equals(commentedId))
                .findFirst().orElse(null)
                .getText();
    }

}
