package com.example.app.parsers

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import com.example.app.StyleProvider
import com.example.app.processors.*

open class ViewParser<T : View> : ViewTypeParser<T>() {
    init {
        attributeProcessorMap["style"] = object : AttributeProcessor<T> {
            override fun process(rawValue: Any, view: T) {
                when (rawValue) {
                    is String -> {
                        if (rawValue.startsWith("@style/")) {
                            val styleName = rawValue.removePrefix("@style/")
                            val applicationContext = view.context.applicationContext
                            if (applicationContext is StyleProvider) {
                                val styleAttributes = applicationContext.getStyle(styleName)
                                if (styleAttributes != null && styleAttributes.isNotEmpty())
                                    apply(styleAttributes, view)
                            }
                        }
                    }
                }
            }
        }
        attributeProcessorMap["background"] = object : DrawableAttributeProcessor<T>() {
            override fun handleValue(drawable: Drawable, view: T) {
                view.background = drawable
            }
        }
        attributeProcessorMap["id"] = object : IdAttributeProcessor<T>() {
            override fun handleValue(value: Int, view: T) {
                view.id = value
            }
        }
        attributeProcessorMap["padding"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPadding(value.toInt(), value.toInt(), value.toInt(), value.toInt())
            }
        }
        attributeProcessorMap["paddingHorizontal"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPadding(value.toInt(), view.paddingTop, value.toInt(), view.paddingBottom)
            }
        }
        attributeProcessorMap["paddingVertical"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPadding(view.paddingLeft, value.toInt(), view.paddingRight, value.toInt())
            }
        }
        attributeProcessorMap["paddingLeft"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPadding(value.toInt(), view.paddingTop, view.paddingRight, view.paddingBottom)
            }
        }
        attributeProcessorMap["paddingTop"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPadding(view.paddingLeft, value.toInt(), view.paddingRight, view.paddingBottom)
            }
        }
        attributeProcessorMap["paddingRight"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPadding(view.paddingLeft, view.paddingTop, value.toInt(), view.paddingBottom)
            }
        }
        attributeProcessorMap["paddingBottom"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPadding(view.paddingLeft, view.paddingTop, view.paddingRight, value.toInt())
            }
        }
        attributeProcessorMap["paddingStart"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPaddingRelative(value.toInt(), view.paddingTop, view.paddingEnd, view.paddingBottom)
            }
        }
        attributeProcessorMap["paddingEnd"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setPaddingRelative(view.paddingStart, view.paddingTop, value.toInt(), view.paddingBottom)
            }
        }
        attributeProcessorMap["visibility"] = object : VisibilityAttributeProcessor<T>() {
            override fun handleValue(value: Int, view: T) {
                view.visibility = value
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun createView(context: Context): T {
        return View(context) as T
    }
}