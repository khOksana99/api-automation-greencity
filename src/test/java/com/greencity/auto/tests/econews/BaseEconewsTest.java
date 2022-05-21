package com.greencity.auto.tests.econews;

import com.greencity.auto.steps.econews.EcoNewsSteps;
import org.testng.annotations.BeforeClass;

public class BaseEconewsTest {
    protected EcoNewsSteps ecoNews;
    protected final Integer CREATED_BY_USER_NEWS_ID = 61;

    @BeforeClass(alwaysRun = true)
    public void beforeBaseEcoNewsTestClass() {
        ecoNews = new EcoNewsSteps();
    }
}
