package com.theopensourcefamily.chessclock

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    fun runOnUIThread(block: () -> Unit) {
        activityRule.runOnUiThread(block)
    }

    @Test
    fun showClocks() {
        runOnUIThread { activityRule.activity.render() }
        onView(withId(R.id.blackClock)).check(matches(isDisplayed()))
        onView(withId(R.id.whiteClock)).check(matches(isDisplayed()))
    }
}
