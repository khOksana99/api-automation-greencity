package com.greencity.auto.constans;

public interface ErrorMsg {
    String WRONG_ID = "Wrong id. Should be 'Long'";
    String HABIT_NOT_EXIST = "Habit does not exist by this id :";
    String ECO_NEWS_NOT_EXIST = "Eco news doesn't exist by this id :";
    String HABIT_ALREADY_ASSIGNED = "Current user already has assigned habit with id:";
    String HABIT_NOT_IN_RANGE = "Can't enroll habit because date input is not in a range from today to it's 7 passed days";
}
