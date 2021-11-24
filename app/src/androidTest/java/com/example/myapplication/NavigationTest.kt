package com.example.myapplication

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @Test
    fun testAbout() {
        launchActivity<MainActivity>()
        //1st screen
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //2nd screen
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //3rd screen
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun testNavigation() {
        launchActivity<MainActivity>()

        //1st screen
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())

        //2nd screen
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())

        //1st screen
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())

        //3rd screen
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())

        //2nd screen
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())

        //1st screen
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

    @Test
    fun testBackstack() {
        val scenario = launchActivity<MainActivity>()

        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        pressBackUnconditionally()
        Assert.assertEquals(scenario.state, Lifecycle.State.DESTROYED)
    }
}