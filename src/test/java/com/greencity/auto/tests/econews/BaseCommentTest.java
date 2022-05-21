package com.greencity.auto.tests.econews;

import com.greencity.auto.steps.econews.EcoNewsCommentSteps;
import com.greencity.auto.utils.Util;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public class BaseCommentTest {
    protected List<Integer> commentsIds = new ArrayList<>();
    protected EcoNewsCommentSteps ecoNewsCommentSteps;
    protected final int newsId = 41;
    protected final String commentText = "comment" + Util.getCurrentDateTime();
    protected int likedComment;
    protected int deletedComment;
    protected int editedComment;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        ecoNewsCommentSteps = new EcoNewsCommentSteps();
        likedComment = ecoNewsCommentSteps.commentEcoNews(newsId, commentText).getCreatedCommentId();
        deletedComment = ecoNewsCommentSteps.commentEcoNews(newsId, commentText).getCreatedCommentId();
        editedComment = ecoNewsCommentSteps.commentEcoNews(newsId, commentText).getCreatedCommentId();
    }

    @AfterClass
    public void deleteComments() {
        if(!commentsIds.isEmpty()) {
            for (int commentId : commentsIds) {
                ecoNewsCommentSteps.deleteCommentEcoNews(commentId);
            }
        }
    }
}
