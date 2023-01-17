package com.coditas.example.test.utils.conditionWatcher

import android.view.View
import androidx.core.view.isVisible

/**
 * Wait till view is visible
 * @param viewId is resource id of view
 */
class WaitForViewToAppear(private var viewId: Int) : Instruction() {
	override val description: String
		get() = "$viewId not found"

	override fun checkCondition(): Boolean {
		return try {
			BaseAndroidTest.currentActivity!!.findViewById<View>(viewId).isVisible
		} catch (e: Exception) {
			false
		}
	}

}