package com.bankwithmint

import android.widget.Toast
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.rule.ActivityTestRule


@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @JvmField
    @Rule
    var mActivityRule:ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test fun test_if_Edit_test_is_More_than_7_letter()
    {
        onView(withId(R.id.cardNumber))
            .perform(typeText("45717360"))

        onView(withId(R.id.get_details))
            .check(matches(isDisplayed()))
            .perform(click())


    }
    @Test fun test_if_Edit_test_is_less_than_7_letter()
    {
        onView(withId(R.id.cardNumber))
            .perform(typeText("45717"))

        onView(withId(R.id.get_details))
            .check(matches(isDisplayed()))
            .perform(click())

    }


}


