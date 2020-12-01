package org.d3ifcool.hicilacap.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.d3ifcool.hicilacap.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testView() {
        val activityScenario = ActivityScenario.launch(SplashScreen::class.java)

        //Wisata
        onView(withId(R.id.wisata)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_wisata)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(2000)
        pressBack()
        pressBack()
        Thread.sleep(2000)

        //kuliner
        onView(withId(R.id.kuliner)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_kuliner)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
        onView(withId(R.id.rv_kategori)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        Thread.sleep(2000)
        pressBack()
        pressBack()
        pressBack()
        Thread.sleep(2000)

        //transportasi
        onView(withId(R.id.transportasi)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_transportasi)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(2000)
        onView(withId(R.id.rv_jenis)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(2000)
        pressBack()
        pressBack()
        pressBack()

        activityScenario.close()
    }
}