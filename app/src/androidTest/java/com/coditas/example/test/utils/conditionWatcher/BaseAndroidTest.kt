package com.coditas.example.test.utils.conditionWatcher

import android.app.Activity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry

abstract class BaseAndroidTest {

    companion object {
        var currentActivity: Activity? = null
            get() {
                InstrumentationRegistry.getInstrumentation().runOnMainSync {
                    val resumedActivities: Collection<*> =
                        ActivityLifecycleMonitorRegistry.getInstance()
                            .getActivitiesInStage(androidx.test.runner.lifecycle.Stage.RESUMED)
                    if (resumedActivities.iterator().hasNext()) {
                        currentActivity = resumedActivities.iterator().next() as Activity?
                    }
                }
                return field
            }
    }

}