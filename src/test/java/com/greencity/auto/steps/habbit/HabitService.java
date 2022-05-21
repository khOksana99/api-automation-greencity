package com.greencity.auto.steps.habbit;

import com.greencity.auto.constans.EndPoint;
import com.greencity.auto.steps.common.BaseSteps;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HabitService extends BaseSteps {
//    @Step("Get all habits")
//    public static Response getAllHabits() {
//        return given()
//                .spec(getRequestSpec(BASE_URI))
//                .when()
//                .get(EndPoint.HABIT.getEndpoint())
//                .then()
//                .extract()
//                .response();
//    }
//
//    @Step("Get habit by id")
//    public static Response getHabitById(int id) {
//        return given()
//                .spec(getRequestSpec(BASE_URI))
//                .when()
//                .get(EndPoint.HABIT.getEndpoint() + "/" + id)
//                .then()
//                .extract()
//                .response();
//    }
//
//    @Step("Get habit shopping list by id")
//    public static Response getHabitShoppingListById(int id) {
//        return given()
//                .spec(getRequestSpec(BASE_URI))
//                .when()
//                .get(EndPoint.HABIT.getEndpoint() + "/" + id + EndPoint.SHOPPING_LIST.getEndpoint())
//                .then()
//                .extract()
//                .response();
//    }
//
//    @Step("Get all habits tags")
//    public static Response getAllHabitsTags() {
//        return given()
//                .spec(getRequestSpec(BASE_URI))
//                .when()
//                .get(EndPoint.HABIT.getEndpoint() + EndPoint.TAGS.getEndpoint())
//                .then()
//                .extract()
//                .response();
//    }
}
