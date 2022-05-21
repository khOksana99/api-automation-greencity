package com.greencity.auto.tests.habits;

import com.greencity.auto.constans.ErrorMsg;
import com.greencity.auto.dataprovider.HabitDataProvider;
import com.greencity.auto.tests.baseTests.BaseHabitsTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

public class HabitProgressTests extends BaseHabitsTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        habitSteps.assignHabitToUser(secondHabitId);
    }

    @Test
    public void completeHabitTest() {
        habitSteps
                .completeHabit(secondHabitId)
                .assertThat()
                .statusCodeIs(SC_OK);
    }

    @Test(dataProvider = "wrongDates", dataProviderClass = HabitDataProvider.class)
    public void completeHabitWithIncorrectDateTest(String date) {
        habitSteps
                .completeHabit(secondHabitId, date)
                .assertThat()
                .statusCodeIs(SC_BAD_REQUEST)
                .assertThat()
                .checkTitleOfErrorMessage(ErrorMsg.HABIT_NOT_IN_RANGE);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        habitSteps.unassignHabit(secondHabitId);
    }
}
