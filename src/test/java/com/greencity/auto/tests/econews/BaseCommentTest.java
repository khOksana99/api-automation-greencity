package com.greencity.auto.tests.econews;

import org.testng.annotations.AfterClass;

import java.util.ArrayList;
import java.util.List;

import static com.greencity.auto.services.EcoNewsCommentService.deleteCommentEcoNews;

public class BaseCommentTest {
    protected List<Integer> commentsIds = new ArrayList<>();

    @AfterClass
    public void deleteComments() {
        if(!commentsIds.isEmpty()) {
            for (int commentId : commentsIds) {
                deleteCommentEcoNews(commentId);
            }
        }
    }
}
