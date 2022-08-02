package com.example.app.processors

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import android.view.View
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

abstract class DrawableAttributeProcessor<in T : View> : AttributeProcessor<T> {
    @Suppress("UNCHECKED_CAST")
    override fun process(rawValue: Any, view: T) {
        val value: Drawable? = when (rawValue) {
            is String -> when {
                rawValue.startsWith("#") -> {
                    val color = Color.parseColor(rawValue)
                    ColorDrawable(color)
                }
                rawValue.startsWith("@drawable/") -> {
                    val drawableResourceId = view.resources.getIdentifier(rawValue.removePrefix("@drawable/"), "drawable", view.context.packageName)
                    ContextCompat.getDrawable(view.context, drawableResourceId)
                }
                rawValue.startsWith("@color/") -> {
                    val colorResourceId = view.resources.getIdentifier(rawValue.removePrefix("@color/"), "color", view.context.packageName)
                    ContextCompat.getDrawable(view.context, colorResourceId)
                }
                else -> {
                    null
                }
            }
            is Map<*, *> -> {
                val objectValue = rawValue as Map<String, Any>
                val type = objectValue.getValue("type").toString()
                when (type) {
                    "ColorDrawable" -> {
                        parseColorDrawable(objectValue)
                    }
                    "GradientDrawable" -> {
                        parseGradientDrawable(objectValue)
                    }
                    else -> null
                }
            }
            else -> null
        }

        if (value != null)
            handleValue(value, view)
    }

    @Suppress("UNCHECKED_CAST")
    private fun parseColorDrawable(objectValue: Map<String, Any>): Drawable {
        val drawable = ColorDrawable()
        if (objectValue.containsKey("color")) {
            val rawColor = objectValue.getValue("color")
            if(rawColor is String) {
                val color = Color.parseColor(rawColor)
                drawable.color = color
            }
        }
        return drawable
    }

    @Suppress("UNCHECKED_CAST")
    private fun parseGradientDrawable(objectValue: Map<String, Any>): Drawable {
        val drawable = GradientDrawable()
        drawable.setColor(Color.BLACK)
        if (objectValue.containsKey("corners")) {
            val corners = objectValue.getValue("corners") as Map<String, Any>
            corners.forEach { k, v ->
                when (k) {
                    "radius" -> {
                        if (v is String) {
                            drawable.cornerRadius = v.toFloat()
                        }
                    }
                }
            }
        }
        if(objectValue.containsKey("solid")) {
            val solid = objectValue.getValue("solid") as Map<String, Any>
            solid.forEach { k, v ->
                when(k) {
                    "color" -> {

                    }
                }
            }
        }
        return drawable
    }

    private fun processObject(rawValue: String, view: T): Drawable? {
        val attributeMap = getAsObject(rawValue)
        return when (attributeMap.getValue("type")) {
            "color" -> {
                val color = Color.parseColor(attributeMap.get("color").toString())
                ColorDrawable(color)
            }
            "GradientDrawable" -> {
                val drawable = GradientDrawable()
                drawable.setColor(Color.BLACK)
                attributeMap.forEach {
                    when (it.key) {
                        "color" -> {
                            drawable.setColor(Color.parseColor(it.value.toString()))
                        }
                        "corners" -> {
                            processCorners(it.value, drawable)
                        }
                        "size" -> {
                            val attributeMap = it.value as Map<String, Any>
                            val width = attributeMap.getValue("width")
                            val height = attributeMap.getValue("height")
                            drawable.bounds = Rect(0, 0, (width as String).toInt(), (height as String).toInt())
                        }
                    }
                }
                drawable
            }
            else -> {
                null
            }
        }
    }

    private fun processCorners(value: Any, drawable: GradientDrawable) {
//        val minorLength = min(view.layoutParams.width, view.layoutParams.height)
//        val factor = 0.5f
        val attributeMap = value as Map<String, Any>
        val radius = attributeMap.withDefault { 0 }.getValue("radius")
        drawable.cornerRadius = (radius as String).toFloat()
    }

    private fun getAsObject(rawValue: String): Map<String, Any> {
        val gson = GsonBuilder().create()
        val type = object : TypeToken<Map<String, Any>>() {}.type
        return gson.fromJson(rawValue, type)
    }

    abstract fun handleValue(drawable: Drawable, view: T)
}