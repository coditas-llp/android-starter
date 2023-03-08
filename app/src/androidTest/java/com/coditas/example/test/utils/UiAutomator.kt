package com.coditas.example.test.utils

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import com.coditas.example.test.utils.conditionWatcher.*
import com.coditas.example.utils.Logger
import com.coditas.example.utils.Logger.logInfo


object UiAutomator {

    private const val APP_NAME = "Android Starter"
    private const val CLASS_TEXTVIEW = "android.widget.TextView"
    private const val CLASS_EDIT_TEXTVIEW = "android.widget.EditText"
    private const val RESOURCE_ID = "com.coditas.example:id/"
    private const val CLASS_BUTTON = "android.widget.Button"
    private const val CLASS_RADIO_BUTTON = "android.widget.RadioButton"
    private const val CLASS_CHECKBOX = "android.widget.CheckBox"


    val device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    /**
     * Wait till given input string is visible on screen for 60 seconds.
     * @param inputString is string we need to validate.
     */
    fun checkTextViewVisibility(inputString: String) {
        ConditionWatcher.waitForCondition(WaitForMultiLineText(inputString))
    }

    fun checkTextView(inputString: String) {
        ConditionWatcher.waitForCondition(WaitForTextToAppear(inputString))
    }

    /**
     * Wait till button with text is visible on screen for 60 seconds.
     * @param inputString is string on button we need to validate.
     */
    fun checkButtonViewVisibility(inputString: String) {
        ConditionWatcher.waitForCondition(WaitForButtonToAppear(inputString))
    }

    /**
     *  Wait till given input string is visible on screen
     *  @param inputString is string we need to validate.
     */
    fun checkButtonViewVisible(inputString: String) {
        val buttonViewId: UiObject = device.findObject(
            UiSelector().text(inputString).className(CLASS_BUTTON)
        )
        if (buttonViewId.exists())
        else throw IllegalArgumentException("No MatchingViewException: No views in hierarchy found matching: ($inputString)")
    }


    /**
     * Wait till radio button with text is visible on screen for 60 seconds.
     * @param inputString is string on button we need to validate.
     */
    fun verifyRadioButtonVisible(inputString: String) {
        ConditionWatcher.waitForCondition(WaitForRadioButtonToAppear(inputString))
    }

    /**
     * Perform click on button having text on screen.
     * @param inputString is text visible on button on which we want to perform click action.
     */
    fun onClickButtonView(inputString: String) {
        val buttonViewId: UiObject = device.findObject(
            UiSelector().text(inputString).className(CLASS_BUTTON)
        )
        if (buttonViewId.exists() && buttonViewId.isEnabled) buttonViewId.click()
        else throw IllegalArgumentException("No MatchingViewException: No views in hierarchy found matching: ($inputString)")
    }

    /**
     * Perform click on radio button having text with index
     * @param inputString is text on which we want to perform click action
     * @param indexId is index position on which we want to perform click action
     */
    fun onClickRadioButton(inputString: String, indexId: Int) {
        val radioButtonId: UiObject = device.findObject(
            UiSelector().className(CLASS_RADIO_BUTTON).index(indexId)
        )
        if (radioButtonId.exists() && radioButtonId.isClickable) radioButtonId.click()
        else throw IllegalArgumentException("NoMatchingViewException: No views in hierarchy found matching: ($inputString)")
    }

    /**
     * Perform click on label/textView having text on screen.
     * @param inputString is text visible on label/textView on which we want to perform click action.
     */
    fun onClickTextView(inputString: String) {
        ConditionWatcher.waitForCondition(WaitForTextToAppear(inputString))
        val textViewId: UiObject = device.findObject(
            UiSelector().text(inputString).className(CLASS_TEXTVIEW)
        )
        if (textViewId.exists()) textViewId.click()
        else throw IllegalArgumentException("No MatchingViewException: No views in hierarchy found matching: $inputString")
    }

    /**
     * Enters given Text into editTextField.
     * @param value is inputString we want to put into field.
     * @param fieldName is name text of EditText [e.g. editText with hint 'Email Address']
     */
    fun enterTextInEditTextField(value: String, fieldName: String) {
        val textViewId: UiObject = device.findObject(
            UiSelector().text(fieldName).className(CLASS_EDIT_TEXTVIEW)
        )
        if (textViewId.exists()){
            textViewId.run {
                click()
                text = value
                closeSoftKeyboard()
            }
        }

        else throw IllegalArgumentException("No MatchingViewException: No views in hierarchy found matching: $fieldName")
    }


    /**
     * Verify Text in description section having more than one line
     * @param inputString is text to verify
     */
    fun onCheckMultiLineTextView(inputString: String) {
        ConditionWatcher.waitForCondition(
            WaitForMultiLineText(
                inputString = inputString
            )
        )
    }


    /**
     * Perform Click action on CheckBox if CheckBox is not checked
     * @param resourceId is viewId of CheckBox
     */
    fun onClickCheckbox(resourceId: String) {
        val checkBoxViewId: UiObject = device.findObject(
            UiSelector().resourceId(RESOURCE_ID + resourceId)
                .className(CLASS_CHECKBOX)
        )
        if (checkBoxViewId.exists() && checkBoxViewId.isChecked.not()) checkBoxViewId.click()
        else throw IllegalArgumentException("No MatchingViewException: No views in hierarchy found matching: $resourceId")
    }

    /**
     * Verify Checkbox is selected
     * @param resourceId is resource Id of checkbox
     */
    fun onVerifyCheckboxSelected(resourceId: String) {
        val checkBoxViewId: UiObject = device.findObject(
            UiSelector().resourceId(RESOURCE_ID + resourceId)
                .className(CLASS_CHECKBOX)
        )
        if (checkBoxViewId.exists() && checkBoxViewId.isChecked) Logger.logInfo("$resourceId is checked")
        else throw IllegalArgumentException("No $resourceId checkbox found")
    }

    /**
     * Make application background
     */
    fun appPause() {
        with(device) {
            pressHome()
        }
    }

    /**
     * Make application foreground
     */
    fun appResume() {
        with(device) {
            pressHome()
            pressRecentApps()
            findObject(UiSelector().descriptionContains(APP_NAME)).click()
        }
    }

    /**
     * Make application background and foreground
     */

    fun softBackgroundApp() {
        appPause()
        appResume()
    }
}