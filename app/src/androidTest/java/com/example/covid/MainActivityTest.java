package com.example.covid;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

//NOTE: make sure emulator is on before running tests
@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_viewIsDisplayed() {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}