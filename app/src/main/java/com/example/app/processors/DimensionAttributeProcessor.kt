package com.example.app.processors

import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

abstract class DimensionAttributeProcessor<in T : View> : AttributeProcessor<T> {
    override fun process(rawValue: Any, view: T) {
        val value: Float = when (rawValue) {
            is String -> when {
                rawValue == "wrap_content" -> {
                    ViewGroup.LayoutParams.WRAP_CONTENT.toFloat()
                }
                rawValue == "match_parent" || rawValue == "fill_parent" -> {
                    ViewGroup.LayoutParams.MATCH_PARENT.toFloat()
                }
                rawValue.endsWith("%") -> {
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_FRACTION, rawValue.removeSuffix("%").toFloat(), view.resources.displayMetrics) / 100
                }
                rawValue.endsWith("dp") -> {
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rawValue.removeSuffix("dp").toFloat(), view.resources.displayMetrics)
                }
                rawValue.startsWith("@dimen/") -> {
                    val dimenResourceId = view.resources.getIdentifier(rawValue.removePrefix("@dimen/"), "dimen", view.context.packageName)
                    view.resources.getDimension(dimenResourceId)
                }
                else -> {
                    rawValue.toFloat()
                }
            }
            is Number -> rawValue.toFloat()
            else -> 0f
        }
        handleValue(value, view)
    }

    abstract fun handleValue(value: Float, view: T)
}