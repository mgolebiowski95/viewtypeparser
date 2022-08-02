package com.example.app.processors

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import java.util.regex.Pattern

abstract class OrientationAttributeProcessor<in T : View> : AttributeProcessor<T> {
    companion object {
        private const val DEFAULT = LinearLayout.HORIZONTAL

        @SuppressLint("RtlHardcoded")
        private val MAP = mapOf(
                Pair("horizontal", LinearLayout.HORIZONTAL),
                Pair("vertical", LinearLayout.VERTICAL)
        )

        private val keys = MAP.keys.joinToString(separator = "|", transform = { it -> "($it)" })
    }

    override fun process(rawValue: Any, view: T) {
        if (rawValue is String) {
            val pattern = Pattern.compile("^($keys)\$")
            val matcher = pattern.matcher(rawValue)
            if (matcher.matches()) {
                val value: Int = if (rawValue.isBlank()) {
                    DEFAULT
                } else {
                    parse(rawValue)
                }
                handleValue(value, view)
            }
        }
    }

    abstract fun handleValue(value: Int, view: T)

    private fun parse(key: String): Int {
        return MAP.getValue(key)
    }
}