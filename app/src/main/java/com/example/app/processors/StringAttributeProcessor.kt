package com.example.app.processors

import android.view.View
import com.example.app.FunctionProvider

abstract class StringAttributeProcessor<in T : View> : AttributeProcessor<T> {
    override fun process(rawValue: Any, view: T) {
        if(rawValue is String) {
            val value = if (rawValue.startsWith("@string/")) {
                val stringResourceId = view.resources.getIdentifier(rawValue.removePrefix("@string/"), "string", view.context.packageName)
                view.resources.getString(stringResourceId)
            } else if(rawValue.startsWith("@fn/")) {
                val functionName = rawValue.removePrefix("@fn/")
                val functionProvider = view.context.applicationContext as FunctionProvider
                val function = functionProvider.getFunction(functionName)
                function?.get()?.toString() ?: rawValue
            }
            else {
                rawValue
            }
            handleValue(value, view)
        }
    }

    abstract fun handleValue(value: String, view: T)
}