package com.example.app.viewtypeparser.processors

import android.annotation.SuppressLint
import android.view.View
import java.util.regex.Pattern

abstract class VisibilityAttributeProcessor<in T : View> : AttributeProcessor<T> {
    companion object {
        private const val DEFAULT_CHILD_VISIBILITY = View.VISIBLE

        @SuppressLint("RtlHardcoded")
        private val VISIBILITY_MAP = mapOf(
                Pair("visible", View.VISIBLE),
                Pair("invisible", View.INVISIBLE),
                Pair("gone", View.GONE)
        )

        private val keys = VISIBILITY_MAP.keys.joinToString(separator = "|", transform = { it -> "($it)" })
    }

    override fun process(rawValue: Any, view: T) {
        if (rawValue is String) {
            val pattern = Pattern.compile("^($keys)\$")
            val matcher = pattern.matcher(rawValue)
            if (matcher.matches()) {
                val value: Int = if (rawValue.isBlank()) {
                    DEFAULT_CHILD_VISIBILITY
                } else {
                    parseVisibility(rawValue)
                }
                handleValue(value, view)
            }
        }
    }

    abstract fun handleValue(value: Int, view: T)

    private fun parseVisibility(key: String): Int {
        return VISIBILITY_MAP.getValue(key)
    }
}