package com.example.app.processors

import android.view.View

abstract class IdAttributeProcessor<T : View> : AttributeProcessor<T> {
    companion object {
        private val idMap = mutableMapOf<String, Int>()
    }

    override fun process(rawValue: Any, view: T) {
        val value = when (rawValue) {
            is Int -> {
                rawValue
            }
            is Float -> {
                rawValue.toInt()
            }
            is Double -> {
                rawValue.toInt()
            }
            is String -> {
                if (rawValue.startsWith("@+id/")) {
                    val idKey = rawValue.removePrefix("@+id/")
                    val identifier = view.resources.getIdentifier(idKey, "id", view.context.packageName)
                    if (identifier != 0) {
                        identifier
                    } else
                        getId(idKey)
                } else if (rawValue.startsWith("@id/")) {
                    val idKey = rawValue.removePrefix("@id/")
                    val identifier = view.resources.getIdentifier(idKey, "id", view.context.packageName)
                    if (identifier == 0)
                        idMap.getOrElse(idKey) { View.NO_ID }
                    else
                        identifier
                } else {
                    View.NO_ID
                }
            }
            else -> View.NO_ID
        }
        handleValue(value, view)
    }

    abstract fun handleValue(value: Int, view: T)

    fun getId(key: String): Int {
        return idMap.getOrPut(key) { View.generateViewId() }
    }
}