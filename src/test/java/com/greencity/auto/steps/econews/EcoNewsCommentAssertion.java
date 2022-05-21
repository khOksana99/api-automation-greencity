package com.greencity.auto.steps.econews;
import com.greencity.auto.steps.common.CommonAssertion;

import static org.hamcrest.Matchers.equalTo;

/**
 * EcoNewsCommentAssertion class is the class for the steps for Eco news comments assertion, containing
 * common methods and fields.
 */
public class EcoNewsCommentAssertion extends CommonAssertion<EcoNewsCommentSteps> {
    public EcoNewsCommentAssertion(EcoNewsCommentSteps caller) {
        super(caller);
    }

    /**
     * Verifies text of eco news comment
     * @param commentedText text of comment
     * @return EcoNewsCommentSteps
     */
    public EcoNewsCommentSteps verifyEcoNewsCommentText(String commentedText) {
        steps.getResponse().then().body("text", equalTo(commentedText));
        return steps;
    }
}
