package com.example.app.viewtypeparser.parsers.parent

import android.view.View
import android.view.ViewGroup
import com.example.app.viewtypeparser.processors.DimensionAttributeProcessor

open class ViewGroupMarginLayoutParamsParser: ViewGroupLayoutParamsParser() {

    init {
        attributeProcessorMap["layout_margin"] = object : DimensionAttributeProcessor<View>() {
            override fun handleValue(value: Float, view: View) {
                val lp = view.layoutParams
                if (lp is ViewGroup.MarginLayoutParams)
                    lp.setMargins(value.toInt(), value.toInt(), value.toInt(), value.toInt())
            }
        }
    }
}