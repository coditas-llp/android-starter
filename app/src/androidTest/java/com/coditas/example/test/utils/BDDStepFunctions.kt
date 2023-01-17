package com.coditas.example.test.utils


import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.LayoutMatchers.hasMultilineText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.coditas.example.test.utils.conditionWatcher.BaseAndroidTest
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import java.util.concurrent.atomic.AtomicReference


object BDDStepFunctions : BaseAndroidTest() {

    /**
     * Verify given input string is multi line text
     * @param viewId is view on which we check whether text is single line or multi line
     */
    fun verifyMultipleLineViewVisibility(viewId: Int) {
        onView(withId(viewId)).check(matches(hasMultilineText()))

    }

    /**
     * Perform Click Action on Done button on Virtual Keyboard
     */
    fun clickOnDoneButtonOnKeyboard(resourceId: Int) {
        onView(withId(resourceId)).perform(ViewActions.pressImeActionButton())
    }

    /**
     * Perform click action on given view
     * @param viewId is view on which click action is going to perform
     */
    fun onClickView(viewId: Int) {
        with(getView(viewId)) {
            checkVisibility()
            click()
        }
    }

    /**
     * Verify view is visible with string input
     * @param viewId is view on which visibility verification is going to perform.
     * @param inputString is given string
     */
    fun checkViewVisibility(viewId: Int, inputString: String) {
        with(getView(viewId)) {
            checkVisibility()
            checkText(inputString)
        }
    }

    /**
     * Used to verify the text on the view
     * @param string to be compared
     */
    fun ViewInteraction.checkText(string: String) {
        check(matches(ViewMatchers.withText(string)))
    }

    /**
     * Verify view is visible with string input
     * @param inputString is given string on which visibility verification is going to perform.
     */
    fun checkViewVisibility(inputString: String) {
        with(getView(inputString)) {
            checkVisibility()
        }
    }

    /**
     * Verify view is visible with string input
     * @param viewId is view on which visibility verification is going to perform.
     */
    fun checkViewVisibility(viewId: Int) {
        with(getView(viewId)) {
            checkVisibility()
        }
    }

    /**
     * Extension for checking if ViewInteraction exist or not
     */
    private fun ViewInteraction.exists(): Boolean {
        val viewExists = AtomicReference<Boolean>()

        this.perform(object : ViewAction {
            override fun perform(uiController: UiController?, view: View?) {
                viewExists.set(view != null)
            }

            override fun getConstraints(): Matcher<View>? {
                return allOf(
                    ViewMatchers.withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                    ),
                    ViewMatchers.isAssignableFrom(View::class.java)
                )
            }

            override fun getDescription(): String {
                return "check if view exists"
            }
        })
        return viewExists.get()
    }

    /**
     * Verify view exist on screen or not
     * @param viewId is id of view on which we perform action
     */
    fun viewExists(viewId: Int): Boolean {
        return try {
            onView(withId(viewId)).exists()
        } catch (e: RuntimeException) {
            false
        }
    }


    /**
     * Perform Click action on spannable text
     *
     */
    fun onClickSpannableText(viewId: Int, inputString: String) {
        onView(withId(viewId)).perform(clickClickableSpan(inputString))
    }


    /**
     * Returns the view in the current visible layout as per the id given
     * @param withId Int
     * @return ViewInteraction
     */
    fun getView(withId: Int): ViewInteraction {
        return onView(
            allOf(
                withId(withId),
                ViewMatchers.isDisplayed()
            )
        )
    }


    /**
     * Returns the view in the current visible layout
     * which has the same text as per given text in the parameter
     * @param withText String
     * @return (androidx.test.espresso.ViewInteraction..androidx.test.espresso.ViewInteraction?)
     */
    fun getView(withText: String): ViewInteraction =
        onView(
            allOf(
                ViewMatchers.withText(withText),
                ViewMatchers.isDisplayed()
            )
        )

    /**
     * Returns the view in current layout which has the id and text same as the params
     * @param withId Int
     * @param withText String
     * @return ViewInteraction
     */
    fun getView(withId: Int, withText: String): ViewInteraction =
        onView(
            allOf(
                ViewMatchers.withText(withText),
                withId(withId),
                ViewMatchers.isDisplayed()
            )
        )


    /**
     * Used to verify the visisbility of the view
     * @param id Int Resource id of the view
     */
    fun checkVisibility(id: Int) {
        getView(id).check(matches(ViewMatchers.isDisplayed()))
    }

    /**
     * Used to verify the visisbility of the view
     * @param ids Int Resource id's of the views
     */
    fun checkVisibility(vararg ids: Int) {
        ids.forEach {
            getView(it).check(matches(ViewMatchers.isDisplayed()))
        }
    }


    /**
     * Used to verify the visisbility of the view
     */
    fun ViewInteraction.checkVisibility() {
        check(matches(ViewMatchers.isDisplayed()))
    }


    /**
     * Method to perform click action
     * @receiver ViewInteraction
     */
    fun ViewInteraction.click() {
        perform(ViewActions.click())
    }


    /**
     * Method to add or replace the text in the EditText
     * @receiver ViewInteraction
     * @param string String
     */
    fun ViewInteraction.replaceText(string: String) {
        perform(ViewActions.replaceText(string), ViewActions.closeSoftKeyboard())
    }

    /**
     * Method to click the spannable text in the textview
     * @param textToClick CharSequence text which is spannable in textview
     * @return ViewAction returns the object of {@link androidx.test.espresso.ViewAction ViewAction}
     */
    private fun clickClickableSpan(textToClick: CharSequence): ViewAction {
        return object : ViewAction {

            override fun getConstraints(): Matcher<View> {
                return Matchers.instanceOf(TextView::class.java)
            }

            override fun getDescription(): String {
                return "clicking on a ClickableSpan"
            }

            override fun perform(uiController: UiController, view: View) {
                val textView = view as TextView
                val spannableString = textView.text as SpannableString

                if (spannableString.isEmpty()) {
                    // TextView is empty, nothing to do
                    throw NoMatchingViewException.Builder()
                        .includeViewHierarchy(true)
                        .withRootView(textView)
                        .build()
                }

                // Get the links inside the TextView and check if we find textToClick
                val spans =
                    spannableString.getSpans(0, spannableString.length, ClickableSpan::class.java)
                if (spans.isNotEmpty()) {
                    var spanCandidate: ClickableSpan
                    for (span: ClickableSpan in spans) {
                        spanCandidate = span
                        val start = spannableString.getSpanStart(spanCandidate)
                        val end = spannableString.getSpanEnd(spanCandidate)
                        val sequence = spannableString.subSequence(start, end)
                        if (textToClick.toString() == sequence.toString()) {
                            span.onClick(textView)
                            return
                        }
                    }
                }
            }
        }
    }
}

