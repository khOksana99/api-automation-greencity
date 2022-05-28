package com.greencity.auto.tests.econews;

import com.greencity.auto.constans.ErrorMsg;
import com.greencity.auto.dataprovider.CommonDataProviders;
import com.greencity.auto.tests.baseTests.BaseEconewsTest;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.*;

@Feature("Get eco news")
public class GetEcoNewsTests extends BaseEconewsTest {
    private final Integer CREATED_BY_USER_NEWS = 5;
    private final String tag = "NEWS";

    @Test(description = "Get all eco news")
    public void getAllEcoNewsTest() {
        ecoNews
                .getAllNews()
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .getAllEcoNewsSchemaIsCorrect();
    }

    @Test(description = "Get eco news by id")
    public void getEcoNewsByIdTest() {
       ecoNews
               .getNewsById(CREATED_BY_USER_NEWS)
               .assertThat()
               .statusCodeIs(SC_OK);
    }

    @Test(description = "Get eco news created by current user1")
    public void getEcoNewsCreatedByCurrentUser() {
        ecoNews
                .getNewsCreatedByCurrentUser()
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .verifyEcoNewsAreCreatedByUser();
    }

    @Test(description = "Get news by tag")
    public void verifyGetEcoNewsByTag() {
        ecoNews
                .getNewsByTag(tag)
                .assertThat()
                .statusCodeIs(SC_OK)
                .assertThat()
                .verifyTags(tag);
    }

    @Test(dataProvider = "wrongNumberDataProvider", dataProviderClass = CommonDataProviders.class)
    public <T> void getEconewsWithInvalidId(T id) {
        ecoNews
                .getNewsById(id)
                .assertThat()
                .statusCodeIs(SC_NOT_FOUND)
                .assertThat()
                .checkTitleOfErrorMessage(ErrorMsg.ECO_NEWS_NOT_EXIST);
    }

    @Test(dataProvider = "specialCharactersDataProvider", dataProviderClass = CommonDataProviders.class)
    public <T> void getEconewsWithStringId(T id) {
        ecoNews
                .getNewsById(id)
                .assertThat()
                .statusCodeIs(SC_BAD_REQUEST)
                .assertThat()
                .checkTitleOfErrorMessage(ErrorMsg.WRONG_ID);
    }
}
