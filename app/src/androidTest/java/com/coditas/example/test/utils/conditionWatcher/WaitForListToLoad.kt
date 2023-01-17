package com.coditas.example.test.utils.conditionWatcher

import androidx.recyclerview.widget.RecyclerView

/**
 * Checks whether recyclerView adapter is not empty
 * @param recyclerViewId is resource id of recyclerView
 */
class WaitForListToLoad(private val recyclerViewId: Int) : Instruction() {

	override val description: String
		get() = "List should be loaded"

	override fun checkCondition(): Boolean {
		return try {
			BaseAndroidTest.currentActivity!!.findViewById<RecyclerView>(recyclerViewId).adapter?.itemCount!! >= 1
		} catch (e: Exception) {
			false
		}
	}
}