package com.example.app.viewtypeparser.parsers.parent

import android.view.View
import com.example.app.viewtypeparser.processors.DimensionAttributeProcessor

open class ViewGroupLayoutParamsParser: ParentParser() {

    init {
        attributeProcessorMap["layout_width"] = object : DimensionAttributeProcessor<View>() {
            override fun handleValue(value: Float, view: View) {
                view.layoutParams.width = value.toInt()
            }
        }
        attributeProcessorMap["layout_height"] = object : DimensionAttributeProcessor<View>() {
            override fun handleValue(value: Float, view: View) {
                view.layoutParams.height = value.toInt()
            }
        }
    }
}