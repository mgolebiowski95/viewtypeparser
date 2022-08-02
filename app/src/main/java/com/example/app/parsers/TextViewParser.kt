package com.example.app.parsers

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import com.example.app.processors.*

open class TextViewParser<T : TextView> : ViewParser<T>() {
    companion object {
        const val TAG = "TextViewParser"
    }

    init {
        attributeProcessorMap["textSize"] = object : AttributeProcessor<T> {
            override fun process(rawValue: Any, view: T) {
                if (rawValue is String) {
                    if (rawValue.endsWith("sp")) {
                        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, rawValue.removeSuffix("sp").toFloat())
                    } else {
                        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, rawValue.toFloat())
                    }
                }
            }
        }
        attributeProcessorMap["textColor"] = object : ColorAttributeProcessor<T>() {
            override fun handleValue(value: Int, view: T) {
                view.setTextColor(value)
            }
        }
        attributeProcessorMap["text"] = object : StringAttributeProcessor<T>() {
            override fun handleValue(value: String, view: T) {
                view.text = value
            }
        }
        attributeProcessorMap["textAllCaps"] = object : BooleanAttributeProcessor<T>() {
            override fun handleValue(value: Boolean, view: T) {
                view.setAllCaps(value)
            }
        }
        attributeProcessorMap["autoSizeTextType"] = object : StringAttributeProcessor<T>() {
            override fun handleValue(value: String, view: T) {
                if (value == "uniform")
                    TextViewCompat.setAutoSizeTextTypeWithDefaults(view, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)
            }
        }
        attributeProcessorMap["font"] = object : AttributeProcessor<T> {
            override fun process(rawValue: Any, view: T) {
                if (rawValue is String) {
                    when {
                        rawValue.startsWith("@font/") -> {
                            val fontResourceId = view.resources.getIdentifier(rawValue.removePrefix("@font/"), "font", view.context.packageName)
                            ResourcesCompat.getFont(view.context, fontResourceId, object : ResourcesCompat.FontCallback() {
                                override fun onFontRetrievalFailed(reason: Int) {
                                    Log.e(TAG, "onFontRetrievalFailed(reason=$reason)")
                                    view.typeface = Typeface.DEFAULT
                                }

                                override fun onFontRetrieved(typeface: Typeface) {
                                    view.typeface = typeface
                                }
                            }, null)
                        }
                        rawValue.startsWith("@assets/") -> {
                            val path = rawValue.removePrefix("@assets/")
                            try {
                                val font = Typeface.createFromAsset(view.resources.assets, path)
                                if (font != null)
                                    view.typeface = font
                            } catch (t: Throwable) {
                                t.printStackTrace()
                            }
                        }
                        rawValue.startsWith("@file/") -> {
                            val path = rawValue.removePrefix("@file/")
                            try {
                                val font = Typeface.createFromFile(path)
                                if (font != null)
                                    view.typeface = font
                            } catch (t: Throwable) {
                                t.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
        attributeProcessorMap["maxLines"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.maxLines = value.toInt()
            }
        }
        attributeProcessorMap["lines"] = object : DimensionAttributeProcessor<T>() {
            override fun handleValue(value: Float, view: T) {
                view.setLines(value.toInt())
            }
        }
        attributeProcessorMap["prefix"] = object : StringAttributeProcessor<T>() {
            override fun handleValue(value: String, view: T) {
                val text = value + view.text.toString()
                view.text = text
            }
        }
        attributeProcessorMap["suffix"] = object : StringAttributeProcessor<T>() {
            override fun handleValue(value: String, view: T) {
                val text = view.text.toString() + value
                view.text = text
            }
        }
        attributeProcessorMap["singleLine"] = object : BooleanAttributeProcessor<T>() {
            override fun handleValue(value: Boolean, view: T) {
                view.setSingleLine(value)
            }
        }
        attributeProcessorMap["drawableLeft"] = object : DrawableAttributeProcessor<T>() {
            override fun handleValue(drawable: Drawable, view: T) {

            }
        }
        attributeProcessorMap["gravity"] = object : GravityAttributeProcessor<T>() {
            override fun handleValue(value: Int, view: T) {
                view.gravity = value
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun createView(context: Context): T {
        return TextView(context) as T
    }
}