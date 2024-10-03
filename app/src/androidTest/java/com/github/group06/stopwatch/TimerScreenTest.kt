package com.github.group06.stopwatch

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.group06.stopwatch.screen.RESET_BUTTON_TAG
import com.github.group06.stopwatch.screen.START_BUTTON_TAG
import com.github.group06.stopwatch.screen.STOP_BUTTON_TAG
import com.github.group06.stopwatch.screen.TimerScreen

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class TimerScreenTest {

    @get:Rule
    val composeRuleTest = createComposeRule()

    @Test
    fun initially_the_screen_is_timer_reset() {

        composeRuleTest.setContent {
            TimerScreen()
        }

        composeRuleTest.onNodeWithTag("timer_0").assertExists()
        composeRuleTest.onNodeWithTag(START_BUTTON_TAG).assertIsEnabled()
        composeRuleTest.onNodeWithTag(STOP_BUTTON_TAG).assertIsNotEnabled()
        composeRuleTest.onNodeWithTag(RESET_BUTTON_TAG).assertIsEnabled()
    }

    @Test
    fun verify_if_the_timer_not_advance_when_is_disabled() {

        composeRuleTest.setContent {
            TimerScreen()
        }

        composeRuleTest.mainClock.advanceTimeBy(milliseconds = 5000L)
        composeRuleTest.onNodeWithTag("timer_0").assertExists()
    }

    @Test
    fun verify_if_the_timer_advance_when_is_enabled_and_start_button_works() {

        composeRuleTest.setContent {
            TimerScreen()
        }

        composeRuleTest.onNodeWithTag(START_BUTTON_TAG).performClick()
        composeRuleTest.mainClock.advanceTimeBy(milliseconds = 5100L)
        composeRuleTest.onNodeWithTag("timer_5").assertExists()
    }

    @Test
    fun verify_if_stop_button_stays_disabled_when_stop_the_timer_and_stop_button_works() {

        composeRuleTest.setContent {
            TimerScreen()
        }

        composeRuleTest.onNodeWithTag(START_BUTTON_TAG).performClick()
        composeRuleTest.mainClock.advanceTimeBy(milliseconds = 5100L)
        composeRuleTest.onNodeWithTag(STOP_BUTTON_TAG).performClick()
        composeRuleTest.onNodeWithTag(STOP_BUTTON_TAG).assertIsNotEnabled()
    }

    @Test
    fun verify_if_start_button_stays_disabled_when_start_the_timer() {

        composeRuleTest.setContent {
            TimerScreen()
        }

        composeRuleTest.onNodeWithTag(START_BUTTON_TAG).performClick()
        composeRuleTest.onNodeWithTag(START_BUTTON_TAG).assertIsNotEnabled()
    }

    @Test
    fun verify_if_the_timer_stop() {

        composeRuleTest.setContent {
            TimerScreen()
        }

        composeRuleTest.onNodeWithTag(START_BUTTON_TAG).performClick()
        composeRuleTest.mainClock.advanceTimeBy(milliseconds = 5100L)
        composeRuleTest.onNodeWithTag(STOP_BUTTON_TAG).performClick()
        composeRuleTest.mainClock.advanceTimeBy(milliseconds = 5000L)
        composeRuleTest.onNodeWithTag("timer_5").assertExists()
    }

    @Test
    fun verify_if_the_timer_resets() {

        composeRuleTest.setContent {
            TimerScreen()
        }

        composeRuleTest.onNodeWithTag(START_BUTTON_TAG).performClick()
        composeRuleTest.mainClock.advanceTimeBy(milliseconds = 5100L)
        composeRuleTest.onNodeWithTag("timer_5").assertExists()
        composeRuleTest.onNodeWithTag(RESET_BUTTON_TAG).performClick()
        composeRuleTest.onNodeWithTag("timer_0").assertExists()
        composeRuleTest.onNodeWithTag(STOP_BUTTON_TAG).assertIsNotEnabled()
        composeRuleTest.onNodeWithTag(START_BUTTON_TAG).assertIsEnabled()
    }

}