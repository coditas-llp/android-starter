package com.coditas.example.test.utils.conditionWatcher

abstract class Instruction {
	abstract val description: String
	abstract fun checkCondition(): Boolean
}