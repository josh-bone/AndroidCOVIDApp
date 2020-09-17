package com.example.covid;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


//NOTE: make sure emulator is on before running tests
public class SymptomDashboardTest {
    @Test
    public void test_viewIsDisplayed() {
        ActivityScenario<SymptomDashboard> scenario = ActivityScenario.launch(SymptomDashboard.class);
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

    @Test
    public void test_titleIsDisplayed() {
        ActivityScenario<SymptomDashboard> scenario = ActivityScenario.launch(SymptomDashboard.class);
        onView(withId(R.id.title)).check(matches(isDisplayed()));
    }

    @Test
    public void test_buttonIsDisplayed() {
        ActivityScenario<SymptomDashboard> scenario = ActivityScenario.launch(SymptomDashboard.class);
        onView(withId(R.id.toSymptoms)).check(matches(isDisplayed()));
    }

    @Test
    public void test_listIsDisplayed() {
        ActivityScenario<SymptomDashboard> scenario = ActivityScenario.launch(SymptomDashboard.class);
        onView(withId(R.id.myListView)).check(matches(isDisplayed()));
    }

}
