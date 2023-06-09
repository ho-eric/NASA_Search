package com.example.nasasearch.ui.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.nasasearch.SetupNavHost
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class HomeScreenKtTest {

    private val firstGridItemNASAId = "201106070070HQ"

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_success_screen_displayed() {
        composeTestRule.setContent {
            SetupNavHost()
        }
        composeTestRule.waitUntilDoesNotExist(hasTestTag(LOADING_SCREEN_TEST_TAG))
        composeTestRule.onNodeWithTag(HOME_SCREEN_TEST_TAG).assertExists()
    }

    @Test
    fun test_home_ui_correct() {
        composeTestRule.setContent {
            SetupNavHost()
        }
        composeTestRule.waitUntilAtLeastOneExists(
            hasContentDescription(
                PHOTO_CARD_CONTENT_DESCRIPTION
            ), 5000
        )
        composeTestRule.onNodeWithTag(SEARCH_BAR_TEST_TAG).assertExists()
        composeTestRule.onNodeWithTag(firstGridItemNASAId).assertExists()
    }

    @Test
    fun test_navigate_to_details_screen() {
        composeTestRule.setContent {
            SetupNavHost()
        }
        composeTestRule.waitUntilAtLeastOneExists(
            hasContentDescription(
                PHOTO_CARD_CONTENT_DESCRIPTION
            ), 5000
        )
        composeTestRule.onNodeWithTag(firstGridItemNASAId).performClick()
        composeTestRule.onNodeWithTag(DETAILS_SCREEN_TEST_TAG).assertExists()
    }

    @Test
    fun test_details_ui_correct() {
        composeTestRule.setContent {
            SetupNavHost()
        }
        composeTestRule.waitUntilAtLeastOneExists(
            hasContentDescription(
                PHOTO_CARD_CONTENT_DESCRIPTION
            ), 5000
        )
        composeTestRule.onNodeWithTag(firstGridItemNASAId).performClick()
        composeTestRule.onNodeWithTag(DETAILS_SCREEN_TEST_TAG).assertExists()
        composeTestRule.onNodeWithTag(DETAILS_SCREEN_IMAGE_TAG).assertExists()
        composeTestRule.onNodeWithTag(DETAILS_SCREEN_TITLE_TAG).assertTextEquals("Expedition 28 Preflight")
        composeTestRule.onNodeWithTag(DETAILS_SCREEN_DATE_TAG).assertTextEquals("2011-06-07T00:00:00Z")
        composeTestRule.onNodeWithTag(DETAILS_SCREEN_DESCRIPTION_TAG).assertTextEquals("Expedition 28 NASA Flight Engineer Mike Fossum, left, Soyuz Commander Sergei Volkov of Russia, and JAXA (Japan Aerospace Exploration Agency) Flight Engineer Satoshi Furukawa, right, have their Russian Sokol suits prepared for launch by a technicians at the Baikonur Cosmodrome in Baikonur, Kazakhstan, Tueday, June 7, 2011.  Fossum, Volkov and Furukawa and launched in their Soyuz TMA-02M rocket from the Baikonur Cosmodrome in Kazakhstan the following morning on June 8th. Photo Credit: (NASA/Roscosmos/Andrey Shelepin)")
    }

    @Test
    fun test_details_screen_back_button_navigation() {
        composeTestRule.setContent {
            SetupNavHost()
        }
        composeTestRule.waitUntilAtLeastOneExists(
            hasContentDescription(
                PHOTO_CARD_CONTENT_DESCRIPTION
            ), 5000
        )
        composeTestRule.onNodeWithTag(firstGridItemNASAId).performClick()
        composeTestRule.onNodeWithTag(BACK_BUTTON_TEST_TAG).performClick()
        composeTestRule.onNodeWithTag(HOME_SCREEN_TEST_TAG).assertExists()
    }
}