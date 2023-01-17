package com.coditas.example.test.utils.conditionWatcher

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector

/**
 * Wait till Button with text appear on screen.
 * @param inputString is text on button view from which we are verifying.
 */
class WaitForButtonToAppear (private val inputString: String) : Instruction() {
	override val description: String
		get() = "Should wait till view is visible"

	override fun checkCondition(): Boolean {
		return try {
			val device: UiDevice =
				UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
			val buttonViewId: UiObject = device.findObject(
				UiSelector().text(inputString).className("android.widget.Button")
			)
			buttonViewId.exists() && buttonViewId.isEnabled
		} catch (e: Exception) {
			false
		}
	}
}