package com.coditas.example.test.utils.conditionWatcher

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector

/**
 * Wait for radio button to appear
 * @param inputString is string attached to radio button
 */
class WaitForRadioButtonToAppear(private val inputString: String) : Instruction() {
	override val description: String
		get() = "Should wait till view is visible"

	override fun checkCondition(): Boolean {
		return try {
			val device: UiDevice =
				UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
			val radioButtonId: UiObject = device.findObject(
				UiSelector().className("android.widget.RadioGroup")
					.resourceId("com.ekodevices.android.staging:id/rgPatientStanceOptions")
			)
				.getChild(UiSelector().className("android.widget.RadioButton").text(inputString)
			)
			radioButtonId.exists()
		} catch (e: Exception) {
			false
		}
	}
}