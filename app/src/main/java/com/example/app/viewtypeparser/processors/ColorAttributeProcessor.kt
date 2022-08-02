package com.example.app.viewtypeparser.processors

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat

abstract class ColorAttributeProcessor<in T : View> : AttributeProcessor<T> {
    override fun process(rawValue: Any, view: T) {
        val value = when (rawValue) {
            is String -> {
                when {
                    rawValue.startsWith("#") -> {
                        val color = Color.parseColor(rawValue)
                        color
                    }
                    rawValue.startsWith("@color/") -> {
                        val colorResourceId = view.resources.getIdentifier(rawValue.removePrefix("@color/"), "color", view.context.packageName)
                        ContextCompat.getColor(view.context, colorResourceId)
                    }
                    else ->
                        0
                }
            }
            else ->
                0
        }
        handleValue(value, view)
    }

    abstract fun handleValue(value: Int, view: T)
}