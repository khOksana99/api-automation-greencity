package com.greencity.auto.tests.habits;

import com.greencity.auto.constans.ErrorMsg;
import com.greencity.auto.dataprovider.CommonDataProviders;
import com.greencity.auto.tests.baseTests.BaseHabitsTest;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.*;

@Feature("Assign habits")
public class AssignHabitTests extends BaseHabitsTest {
    @Test(priority = 1)
    public void assignHabitTest() {
        habitSteps
                .assignHabitToUser(firstHabitId)
                .assertThat()
                .statusCodeIs(SC_CREATED)
                .assertThat()
                .verifyHabitIsInProgress();

        habitIds.add(firstHabitId);
    }

    @Test(priority = 2)
    public void assignAlreadyAssignedHabitTest() {
        habitSteps
                .assignHabitToUser(firstHabitId)
                .assertThat()
                .statusCodeIs(SC_BAD_REQUEST)
                .assertThat()
                .checkTitleOfErrorMessage(ErrorMsg.HABIT_ALREADY_ASSIGNED);
    }

    @Test(priority = 3)
    public void unassignHabitTest() {
        habitSteps
                .unassignHabit(firstHabitId)
                .assertThat()
                .statusCodeIs(SC_OK);    }

    @Test(dataProvider = "wrongNumberDataProvider", dataProviderClass = CommonDataProviders.class)
    public <T> void assignHabitWithIncorrectId(T id) {
        habitSteps
                .assignHabitToUser(id)
                .assertThat()
                .statusCodeIs(SC_NOT_FOUND)
                .assertThat()
                .checkTitleOfErrorMessage(ErrorMsg.HABIT_NOT_EXIST);
    }
}
