package com.example.covid;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class SymptomSurveyActivityTest {
    @Test
    public void test_viewIsDisplayed() {
        ActivityScenario<SymptomSurveyActivity> scenario = ActivityScenario.launch(SymptomSurveyActivity.class);
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

    @Test
    public void test_buttonIsDisplayed() {
        ActivityScenario<SymptomSurveyActivity> scenario = ActivityScenario.launch(SymptomSurveyActivity.class);
        onView(withId(R.id.button)).check(matches(isDisplayed()));
    }

    @Test
    public void test_feverEditTextIsDisplayed(){
        ActivityScenario<SymptomSurveyActivity> scenario = ActivityScenario.launch(SymptomSurveyActivity.class);
        onView(withId(R.id.editTextNumberDecimal)).check(matches(isDisplayed()));
    }

    @Test
    public void test_checkboxesAreDisplayed(){
        ActivityScenario<SymptomSurveyActivity> scenario = ActivityScenario.launch(SymptomSurveyActivity.class);
        onView(withId(R.id.checkBox)).check(matches(isDisplayed()));
        onView(withId(R.id.checkBox2)).check(matches(isDisplayed()));
        onView(withId(R.id.checkBox3)).check(matches(isDisplayed()));
        onView(withId(R.id.checkBox4)).check(matches(isDisplayed()));
        onView(withId(R.id.checkBox5)).check(matches(isDisplayed()));
        onView(withId(R.id.checkBox6)).check(matches(isDisplayed()));
        onView(withId(R.id.checkBox7)).check(matches(isDisplayed()));
        onView(withId(R.id.checkBox8)).check(matches(isDisplayed()));
        onView(withId(R.id.checkBox9)).check(matches(isDisplayed()));
    }

    @Test
    public void test_textViewsAreDisplayed(){
        ActivityScenario<SymptomSurveyActivity> scenario = ActivityScenario.launch(SymptomSurveyActivity.class);
        onView(withId(R.id.textView3)).check(matches(isDisplayed())); //"Temperature: "
        onView(withId(R.id.textView4)).check(matches(isDisplayed())); //"Check all that apply"
    }

}