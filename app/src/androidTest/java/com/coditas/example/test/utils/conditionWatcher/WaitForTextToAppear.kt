package com.coditas.example.test.utils.conditionWatcher

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector

/**
 * Wait till input text appears on screen
 * @param inputString is string we are verifying.
 */
class WaitForTextToAppear(private val inputString: String) : Instruction() {
	override val description: String
		get() = "Should wait till view is visible"

	override fun checkCondition(): Boolean {
		return try {
			val device: UiDevice =
				UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
			val textViewId: UiObject = device.findObject(
				UiSelector().textContains(inputString).className("android.widget.TextView")
			)
			textViewId.exists()
		} catch (e: Exception) {
			false
		}
	}
}