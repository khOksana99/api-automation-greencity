package com.greencity.auto.steps.habbit;

import com.greencity.auto.steps.common.CommonAssertion;
import io.qameta.allure.Step;

import static org.hamcrest.Matchers.equalTo;

/**
 * HabitAssertion class is the class for the steps for habits assertion, containing
 * common methods and fields.
 */
public class HabitAssertion extends CommonAssertion<HabitSteps> {
    public HabitAssertion(HabitSteps caller) {
        super(caller);
    }

    /**
     * Verifies json schema from get all habits request
     * @return econews steps
     */
    @Step("Verify json schema for all habits")
    public HabitSteps getAllHabitsSchemaIsCorrect() {
        checkJsonSchema("getAllHabits.json");
        return steps;
    }

    /**
     * Verifies json schema from get all habits request
     * @return econews steps
     */
    @Step("Verify json schema for shopping list")
    public HabitSteps getShoppingListSchemaIsCorrect() {
        checkJsonSchema("shopingList.json");
        return steps;
    }

    /**
     * Verifies id is equal to expected
     * @param id expected id
     * @return habit steps
     */
    @Step("Verify id is proper")
    public<T> HabitSteps verifyIdIsProper(T id) {
        steps.getResponse().then().body("id", equalTo(id));
        return steps;
    }
}
