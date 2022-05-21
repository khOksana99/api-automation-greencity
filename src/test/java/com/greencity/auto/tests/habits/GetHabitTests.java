package com.greencity.auto.tests.habits;

import com.greencity.auto.constans.ErrorMsg;
import com.greencity.auto.dataprovider.CommonDataProviders;
import com.greencity.auto.tests.baseTests.BaseHabitsTest;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.*;

@Feature("Get habits")
public class GetHabitTests extends BaseHabitsTest {
    @Test
    public void getAllHabitsTest() {
        habitSteps
                .getAllHabits()
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .getAllHabitsSchemaIsCorrect();
    }

    @Test
    public void getHabitByIdTest() {
        habitSteps
                .getHabitById(firstHabitId)
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .verifyIdIsProper(firstHabitId);
    }

    @Test
    public void getShoppingListByIdTest() {
        habitSteps
                .getHabitShoppingListById(firstHabitId)
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .getShoppingListSchemaIsCorrect();
    }

    @Test(dataProvider = "wrongNumberDataProvider", dataProviderClass = CommonDataProviders.class)
    public <T> void getHabitWithInvalidId(T id) {
        habitSteps
                .getHabitById(id)
                .assertThat()
                .statusCodeIs(SC_NOT_FOUND)
                .assertThat()
                .checkTitleOfErrorMessage(ErrorMsg.HABIT_NOT_EXIST);
    }

    @Test(dataProvider = "specialCharactersDataProvider", dataProviderClass = CommonDataProviders.class)
    public <T> void getHabitWithStringId(T id) {
        habitSteps
                .getHabitById(id)
                .assertThat()
                .statusCodeIs(SC_BAD_REQUEST)
                .assertThat()
                .checkTitleOfErrorMessage(ErrorMsg.WRONG_ID);
    }
}
