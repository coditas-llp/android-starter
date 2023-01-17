package com.coditas.example.test.utils.conditionWatcher

import android.widget.TextView

/**
 * Checks whether view is visible with text
 * @param viewId is resource id for the textView
 * @param inputString is string visible on textView
 */
class WaitForTextViewToAppear(private val viewId: Int, private val inputString: String) : Instruction() {
	override val description: String
		get() = "Should wait till view is visible"

	override fun checkCondition(): Boolean {
		return try {
			(BaseAndroidTest.currentActivity!!).findViewById<TextView>(viewId)?.text.toString()
				.equals(inputString, true)
		} catch (e: Exception) {
			false
		}
	}
}