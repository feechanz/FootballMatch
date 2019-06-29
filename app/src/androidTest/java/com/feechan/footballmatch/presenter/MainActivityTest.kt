package com.feechan.footballmatch.presenter

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.feechan.footballmatch.R
import com.feechan.footballmatch.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

//    @Test
//    fun testRecyclerViewBehaviour() {
//        Thread.sleep(5000)
//        onView(withId(matchRecyclerView))
//                .check(matches(isDisplayed()))
//        onView(withId(matchRecyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
//        onView(withId(matchRecyclerView)).perform(
//                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
//    }
//
//    @Test
//    fun testSelectBottomNavigation() {
//        Thread.sleep(5000)
//        onView(withId(bottomNavigationView))
//                .check(matches(isDisplayed()))
//        onView(withId(R.id.next_match_menu)).perform(click())
//    }


    //This testing skenario
    //1. Wait 5 sec for loading Prev Match Data
    //2. Select First Item from Prev Match Recycler View
    //3. Wait 5 sec for loading detail Match Event
    //4. Add this Match as Favorite
    //5. Press Back
    //6. Select Next Match Tab
    //7. Wait 5 sec for loading Next Match Data
    //8. Select third item from Next Match Recycler View
    //9. Add this Match as Favorite
    //10. Press Back
    //11. Select Favorite Tab
    //12. Select First Item from Favorite Recycler View
    //13. Remove this Match from Favorite
    //14. Press Back
    //15. Swipe Favorite to refresh data
    //16. Wait 5 second until closed
    @Test
    fun addFavoritePrevNum1(){
        //WAIT LOADING PREV MATCH, OPEN ITEM 1
        Thread.sleep(5000)
        onView(withId(matchPrevRecyclerView))
                .check(matches(isDisplayed()))
        onView(withId(matchPrevRecyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(matchPrevRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //WAIT LOADING DETAIL MATCH
        Thread.sleep(5000)
        onView(withId(R.id.add_to_favorite)).perform(click())

        pressBack()

        //SCROLL AND SELECT ITEM IN POSITION 15
        onView(withId(R.id.next_match_menu)).perform(click())
        Thread.sleep(5000)
        onView(withId(matchNextRecyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(matchNextRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        //WAIT LOADING DETAIL MATCH
        Thread.sleep(5000)
        onView(withId(R.id.add_to_favorite)).perform(click())

        pressBack()

        //OPEN FAVORITES PAGE, OPEN ITEM 1
        onView(withId(R.id.favorites)).perform(click())
        onView(withId(matchFavoriteRecyclerView))
                .check(matches(isDisplayed()))
        onView(withId(matchFavoriteRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //REMOVE FIRST ITEM FROM FAVORITES
        onView(withId(R.id.add_to_favorite)).perform(click())

        pressBack()

        onView(withId(R.id.swipeRefreshLayout)).perform(swipeDown());
        Thread.sleep(5000)
    }
}