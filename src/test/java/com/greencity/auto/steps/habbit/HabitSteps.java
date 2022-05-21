package com.greencity.auto.steps.habbit;

import com.greencity.auto.constans.EndPoint;
import com.greencity.auto.steps.common.BaseSteps;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

/**
 * HabitSteps class is the class for all steps related to habit, containing
 * common methods and fields.
 */
public class HabitSteps extends BaseSteps<HabitSteps> {
    private final HabitAssertion assertion = new HabitAssertion(this);

    public HabitSteps() {
        super();
    }

    /**
     * A method used to begin assertion
     * @return EcoNewsAssertion object
     */
    @Override
    public HabitAssertion assertThat() {
        return assertion;
    }

    /**
     * Get all habits
     * @return this
     */
    @Step("Get all habits")
    public <T> HabitSteps getAllHabits() {
        response = given()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.HABIT.getEndpoint())
                .then()
                .extract()
                .response();
        return this;
    }

    /**
     * Get habit by id
     * @param id habit id
     * @return this
     */
    @Step("Get habit by id")
    public <T> HabitSteps getHabitById(T id) {
        response = given()
                .spec(getRequestSpec())
                .when().log().all()
                .get(EndPoint.HABIT.getEndpoint() + "/" + id)
                .then().log().all()
                .extract()
                .response();
        return this;
    }

    /**
     * Get habit shopping list by id
     * @param id habit id
     * @return this
     */
    @Step("Get habit shopping list by id")
    public <T> HabitSteps getHabitShoppingListById(T id) {
        response = given()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.HABIT.getEndpoint() + "/" + id + EndPoint.SHOPPING_LIST.getEndpoint())
                .then()
                .extract()
                .response();
        return this;
    }

    /**
     * Get all habit's tags
     * @return this
     */
    @Step("Get all habits tags")
    public <T> HabitSteps getAllHabitsTags() {
        response = given()
                .spec(getRequestSpec())
                .when()
                .get(EndPoint.HABIT.getEndpoint() + EndPoint.TAGS.getEndpoint())
                .then()
                .extract()
                .response();
        return this;
    }
}
