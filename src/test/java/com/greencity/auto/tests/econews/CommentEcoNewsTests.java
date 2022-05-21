package com.greencity.auto.tests.econews;

import com.greencity.auto.utils.Util;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class CommentEcoNewsTests extends BaseCommentTest {
    private final String editedCommentText = "editedComment" + Util.getCurrentDateTime();

    @Test(description = "Comment eco news")
    public void commentEcoNewsTest() {
        int id = ecoNewsCommentSteps.commentEcoNews(newsId, commentText).getCreatedCommentId();
        System.out.println(id);
        ecoNewsCommentSteps
                .commentEcoNews(newsId, commentText)
                .assertThat()
                .statusCodeIs(SC_CREATED)
                .assertThat()
                .verifyEcoNewsCommentText(commentText);
    }

    @Test(description = "Delete comment eco news")
    public void deleteCommentEcoNewsTest() {
        ecoNewsCommentSteps.
        deleteCommentEcoNews(deletedComment)
                .assertThat()
                .statusCodeIs(SC_OK);
    }

    @Test(description = "Like comment eco news")
    public void likeCommentEcoNewsTest() {
        ecoNewsCommentSteps.
        likeCommentEcoNews(likedComment)
                .assertThat()
                .statusCodeIs(SC_OK);
    }

    @Test(description = "Edit comment eco news")
    public void editCommentTest() {
        ecoNewsCommentSteps
                .editCommentEcoNews(editedComment, newsId, editedCommentText)
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .verifyTextChanged(ecoNewsCommentSteps.getTextOfCommentById(newsId, editedComment), editedCommentText);
    }
}
