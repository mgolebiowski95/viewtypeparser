package com.example.app.processors

import android.view.View

abstract class BooleanAttributeProcessor<in T : View> : AttributeProcessor<T> {
    override fun process(rawValue: Any, view: T) {
        val value = when (rawValue) {
            is Boolean -> rawValue
            is String -> rawValue.toBoolean()
            else -> false
        }
        handleValue(value, view)
    }

    abstract fun handleValue(value: Boolean, view: T)
}