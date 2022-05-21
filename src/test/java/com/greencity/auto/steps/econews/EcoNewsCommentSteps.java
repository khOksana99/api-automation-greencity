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
     * @return EcoNewsAssertion object
     */
    @Override
    public EcoNewsCommentAssertion assertThat() {
        return assertion;
    }

    /**
     * Comment eco news
     * @param id eco news id
     * @param text text of comment
     * @return EcoNewsCommentSteps
     */
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

    /**
     * Edit comment eco news
     * @param  commentId id of comment
     * @param newsId eco news id
     * @param text text of comment
     * @return EcoNewsCommentSteps
     */
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

    /**
     * Delete comment
     * @param commentId id of comment
     * @return EcoNewsCommentSteps
     */
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

    /**
     * Get all comments
     * @param newsId eco news id
     * @return EcoNewsCommentSteps
     */
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

    /**
     * Count of comments
     * @param newsId eco news id
     * @return EcoNewsCommentSteps
     */
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

    /**
     * Like comment
     * @param commentId
     * @return EcoNewsCommentSteps
     */
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

    /**
     * Get id of created comment
     * @return id
     */
    @Step("Get id of last eco news")
    public Integer getCreatedCommentId() {
        return response.jsonPath().getInt("id");
    }

    /**
     * Get text of comment by id
     * @param newsId eco news id
     * @param commentedId comment id
     * @return text of comment
     */
    @Step("Get comment text by id")
    public String getTextOfCommentById(int newsId, int commentedId) {
       return getAllCommentsEcoNews(newsId).getResponse().jsonPath().getList("page", Comment.class)
                .stream()
                .filter(comment -> comment.getId().equals(commentedId))
                .findFirst().orElse(null)
                .getText();
    }

}
