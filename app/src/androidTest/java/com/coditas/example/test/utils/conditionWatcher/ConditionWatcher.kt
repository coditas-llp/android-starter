package com.coditas.example.test.utils.conditionWatcher

import com.coditas.example.utils.Logger
import com.coditas.example.utils.Logger.logError
import com.coditas.example.utils.Logger.logInfo
import javax.inject.Singleton

class ConditionWatcher {

	private var timeoutLimit = DEFAULT_TIMEOUT_LIMIT
	private var watchInterval = DEFAULT_INTERVAL
	private var viewVisibilityTimeoutLimit = DEFAULT_TIMEOUT_VIEW_VISIBILITY_LIMIT

	companion object {

		private const val CONDITION_NOT_MET = 0
		private const val CONDITION_MET = 1
		private const val TIMEOUT = 2
		const val DEFAULT_TIMEOUT_LIMIT = 1000 * 60
		const val DEFAULT_TIMEOUT_VIEW_VISIBILITY_LIMIT = 1000 * 20
		const val DEFAULT_INTERVAL = 250
		private var conditionWatcher: ConditionWatcher? = null

		@Singleton
		val instance: ConditionWatcher?
			get() {
				if (conditionWatcher == null) {
					conditionWatcher = ConditionWatcher()
				}
				return conditionWatcher
			}

		@Throws(Exception::class)
		fun waitForCondition(instruction: Instruction) {
			var status = CONDITION_NOT_MET
			var elapsedTime = 0
			print("ConditionWatcher :  Checking for condition")
			do {
				if (instruction.checkCondition()) {
					status = CONDITION_MET
				} else {
					elapsedTime += instance!!.watchInterval
					Thread.sleep(instance!!.watchInterval.toLong())
				}
				if (elapsedTime >= instance!!.timeoutLimit) {
					status = TIMEOUT
					break
				}
			} while (status != CONDITION_MET)
			if (status == TIMEOUT)
				throw Exception(
					instruction.description + " - took more than "
							+ instance!!.timeoutLimit / 1000 + " seconds. Test stopped."
				)
		}

		fun waitForViewCondition(instruction: Instruction){
			var status = CONDITION_NOT_MET
			var elapsedTime = 0
			print("ConditionWatcher :  Checking for view visibility")
			do {
				if (instruction.checkCondition()) {
					status = CONDITION_MET
				} else {
					elapsedTime += instance!!.watchInterval
					Thread.sleep(instance!!.watchInterval.toLong())
				}
				if (elapsedTime >= instance!!.viewVisibilityTimeoutLimit) {
					status = TIMEOUT
					break
				}
			} while (status != CONDITION_MET)
			if (status == TIMEOUT) Logger.logError(" Error View :- ${instruction.description}")
		}
	}

}