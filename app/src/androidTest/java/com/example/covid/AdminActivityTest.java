package com.example.covid;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


//NOTE: make sure emulator is on before running tests
public class AdminActivityTest {
    @Test
    public void test_viewIsDisplayed() {
        ActivityScenario<AdminActivity> scenario = ActivityScenario.launch(AdminActivity.class);
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

    @Test
    public void test_titleIsDisplayed(){
        ActivityScenario<AdminActivity> scenario = ActivityScenario.launch(AdminActivity.class);
        onView(withId(R.id.title)).check(matches(isDisplayed()));
    }

    @Test
    public void test_buttonIsDisplayed(){
        ActivityScenario<AdminActivity> scenario = ActivityScenario.launch(AdminActivity.class);
        onView(withId(R.id.toSymptoms)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isListDisplayed(){
        ActivityScenario<AdminActivity> scenario = ActivityScenario.launch(AdminActivity.class);
        onView(withId(R.id.myListView)).check(matches(isDisplayed()));
    }
}