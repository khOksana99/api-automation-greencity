package com.greencity.auto.steps.econews;
import com.greencity.auto.steps.common.CommonAssertion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EcoNewsCommentAssertion extends CommonAssertion<EcoNewsCommentSteps> {
    public EcoNewsCommentAssertion(EcoNewsCommentSteps caller) {
        super(caller);
    }

    public EcoNewsCommentSteps verifyEcoNewsCommentText(String commentedText) {
        steps.getResponse().then().body("text", equalTo(commentedText));
        return steps;
    }
}
