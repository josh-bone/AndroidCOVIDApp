package com.example.covid;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class InfoDashActivityTest {
    @Test
    public void test_textViewsAreDisplayed(){
        ActivityScenario<AdminActivity> scenario = ActivityScenario.launch(AdminActivity.class);
        onView(withId(R.id.confirmedTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmedNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.dateText)).check(matches(isDisplayed()));
    }
}