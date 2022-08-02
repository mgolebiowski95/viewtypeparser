package com.example.app.viewtypeparser.processors

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import java.util.regex.Pattern

abstract class GravityAttributeProcessor<in T : View> : AttributeProcessor<T> {
    companion object {
        private const val DEFAULT_CHILD_GRAVITY = Gravity.TOP or Gravity.START

        @SuppressLint("RtlHardcoded")
        private val GRAVITY_MAP = mapOf(
                Pair("top", Gravity.TOP),
                Pair("bottom", Gravity.BOTTOM),
                Pair("left", Gravity.LEFT),
                Pair("right", Gravity.RIGHT),
                Pair("center_vertical", Gravity.CENTER_VERTICAL),
                Pair("fill_vertical", Gravity.FILL_VERTICAL),
                Pair("center_horizontal", Gravity.CENTER_HORIZONTAL),
                Pair("fill_horizontal", Gravity.FILL_HORIZONTAL),
                Pair("center", Gravity.CENTER),
                Pair("fill", Gravity.FILL),
                Pair("clip_vertical", Gravity.CLIP_VERTICAL),
                Pair("clip_horizontal", Gravity.CLIP_HORIZONTAL),
                Pair("start", Gravity.START),
                Pair("end", Gravity.END)
        )

        private val keys = GRAVITY_MAP.keys.joinToString(separator = "|", transform = { it -> "($it)" })
    }

    override fun process(rawValue: Any, view: T) {
        if (rawValue is String) {
            val pattern = Pattern.compile("^($keys)(\\|($keys))*\$")
            val matcher = pattern.matcher(rawValue)
            if (matcher.matches()) {
                val gravityArray = rawValue.split('|')
                var value: Int
                if (gravityArray.isEmpty()) {
                    value = DEFAULT_CHILD_GRAVITY
                } else {
                    value = parseGravity(gravityArray[0])
                    for (index in 1 until gravityArray.size)
                        value = value.or(parseGravity(gravityArray[index]))
                }
                handleValue(value, view)
            }
        }
    }

    abstract fun handleValue(value: Int, view: T)

    private fun parseGravity(key: String): Int {
        return GRAVITY_MAP.getValue(key)
    }
}