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
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }
}