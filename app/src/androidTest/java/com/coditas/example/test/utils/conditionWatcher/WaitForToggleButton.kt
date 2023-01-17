package com.coditas.example.test.utils.conditionWatcher

import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import com.coditas.example.test.utils.UiAutomator

class WaitForToggleButton(private val resourceId: String) : Instruction() {
	override val description: String
		get() = "Should wait till view is visible"

	override fun checkCondition(): Boolean {
		return try {
			val checkBoxView: UiObject = UiAutomator.device.findObject(
				UiSelector().className("android.widget.Switch")
					.resourceId("com.ekodevices.android.staging:id/$resourceId")
			)
			checkBoxView.exists()
		} catch (e: Exception) {
			false
		}
	}
}