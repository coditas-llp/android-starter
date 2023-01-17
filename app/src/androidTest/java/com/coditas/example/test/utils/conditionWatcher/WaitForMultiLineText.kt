package com.coditas.example.test.utils.conditionWatcher

import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import com.coditas.example.test.utils.UiAutomator
import com.coditas.example.utils.Logger.debug


/**
 * Wait for input Text to appear
 * @param inputString is string we want to verify on screen
 */
class WaitForMultiLineText(private val inputString: String) : Instruction() {
    override val description: String
        get() = "Should wait till view is visible"

    companion object {
        const val CLASS_TEXT_VIEW = "android.widget.TextView"
    }

    override fun checkCondition(): Boolean {
        return try {
            val textViewId: UiObject =
                UiAutomator.device.findObject(
                    UiSelector().className(CLASS_TEXT_VIEW)
                        .textContains(inputString.substring(0, inputString.length))
                )
            //here if textview id does not exists then extract first 10 letters and replace next line with single space
            if (textViewId.exists().not()) {
                val alternateTextViewId: UiObject =
                    UiAutomator.device.findObject(
                        UiSelector().className(CLASS_TEXT_VIEW)
                            .textContains(inputString.substring(0, 10))
                    )
                if (alternateTextViewId.exists().not()) {
                    return false
                }
                val textViewContent = alternateTextViewId.text.replace("\n", " ")
                val result = alternateTextViewId.exists() && textViewContent.replace("  ", " ")
                    .contains(inputString)
                result
            } else {
                val textViewContent = textViewId.text.replace("\n", " ")
                textViewId.exists() && textViewContent.replace("  ", " ").contains(inputString)

            }
        } catch (e: Exception) {
            debug("Multi Line Exception :- $e")
            return false
        }
    }
}