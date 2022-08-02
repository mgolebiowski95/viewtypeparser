package com.example.app.viewtypeparser.parsers

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.example.app.viewtypeparser.processors.DrawableAttributeProcessor

open class ImageViewParser<T : ImageView> : ViewParser<T>() {
    init {
        attributeProcessorMap["src"] = object : DrawableAttributeProcessor<T>() {
            override fun handleValue(drawable: Drawable, view: T) {
                view.setImageDrawable(drawable)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun createView(context: Context): T {
        return ImageView(context) as T
    }
}