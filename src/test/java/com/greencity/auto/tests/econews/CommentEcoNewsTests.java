package com.greencity.auto.tests.econews;

import com.greencity.auto.pojo.Comment;
import com.greencity.auto.utils.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.greencity.auto.services.EcoNewsCommentService.*;
import static com.greencity.auto.services.EcoNewsService.getLastEcoNewsId;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class CommentEcoNewsTests extends BaseCommentTest {
    private int newsId;
    private String commentText = "comment" + Util.getCurrentDateTime();
    private String editedCommentText = "editedComment" + Util.getCurrentDateTime();
    private int likedComment;
    private int deletedComment;
    private int editedComment;

    @BeforeClass
    public void setUp() {
        newsId = getLastEcoNewsId();
        likedComment = getCreatedCommentId(newsId, commentText);
        deletedComment = getCreatedCommentId(newsId, commentText);
        editedComment = getCreatedCommentId(newsId, commentText);

        commentsIds.add(likedComment);
        commentsIds.add(deletedComment);
        commentsIds.add(editedComment);
    }

    @Test(description = "Comment eco news")
    public void commentEcoNewsTest() {
        commentEcoNews(newsId, commentText)
                .then()
                .assertThat()
                .statusCode(SC_CREATED)
                .body("text", equalTo(commentText));
    }

    @Test(description = "Delete comment eco news")
    public void deleteCommentEcoNewsTest() {
        deleteCommentEcoNews(deletedComment)
                .then()
                .assertThat()
                .statusCode(SC_OK);
    }

    @Test(description = "Like comment eco news")
    public void likeCommentEcoNewsTest() {
        likeCommentEcoNews(likedComment)
                .then()
                .assertThat()
                .statusCode(SC_OK);
    }

    @Test(description = "Edit comment eco news")
    public void editCommentTest() {
        editCommentEcoNews(editedComment, newsId, editedCommentText)
                .then()
                .assertThat()
                .statusCode(SC_OK);

        String commentTextAfterEditing = getAllCommentsEcoNews(newsId).jsonPath().getList("page", Comment.class)
                .stream()
                .filter(comment -> comment.getId().equals(editedComment))
                .findFirst().orElse(null)
                .getText();

        Assert.assertEquals(commentTextAfterEditing, editedCommentText);
    }
}
