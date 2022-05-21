package com.greencity.auto.tests.baseTests;

import com.greencity.auto.steps.habbit.HabitSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public class BaseHabitsTest {
    protected HabitSteps habitSteps;
    protected final Integer firstHabitId = 1;
    protected final Integer secondHabitId = 2;
    protected List<Integer> habitIds = new ArrayList<>();

    @BeforeClass(alwaysRun = true)
    public void beforeBaseHabitTestClass() {
        habitSteps = new HabitSteps();
    }

    @AfterClass(alwaysRun = true)
    public void unassignHabit() {
        if(!habitIds.isEmpty()) {
            for (int id : habitIds) {
                habitSteps.unassignHabit(id);
            }
        }
    }
}
