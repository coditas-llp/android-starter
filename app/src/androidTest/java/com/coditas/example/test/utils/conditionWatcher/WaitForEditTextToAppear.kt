package com.coditas.example.test.utils.conditionWatcher

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector

/**
 * Wait for Edit Text Field to appear
 * @param inputString is string within edit text
 */
class WaitForEditTextToAppear(private val inputString: String) : Instruction() {
	override val description: String
		get() = "Should wait till view is visible"

	override fun checkCondition(): Boolean {
		return try {
			val device: UiDevice =
				UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
			val textViewId: UiObject = device.findObject(
				UiSelector().text(inputString).className("android.widget.EditText")
			)
			textViewId.exists()
		} catch (e: Exception) {
			false
		}
	}
}