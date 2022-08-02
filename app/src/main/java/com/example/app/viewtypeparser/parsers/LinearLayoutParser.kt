package com.example.app.viewtypeparser.parsers

import android.content.Context
import android.widget.LinearLayout
import com.example.app.viewtypeparser.processors.GravityAttributeProcessor
import com.example.app.viewtypeparser.processors.OrientationAttributeProcessor

class LinearLayoutParser<T : LinearLayout> : ViewGroupParser<T>() {

    init {
        attributeProcessorMap["gravity"] = object : GravityAttributeProcessor<T>() {
            override fun handleValue(value: Int, view: T) {
                view.gravity = value
            }
        }
        attributeProcessorMap["orientation"] = object : OrientationAttributeProcessor<T>() {
            override fun handleValue(value: Int, view: T) {
                view.orientation = value
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun createView(context: Context): T {
        return LinearLayout(context) as T
    }
}