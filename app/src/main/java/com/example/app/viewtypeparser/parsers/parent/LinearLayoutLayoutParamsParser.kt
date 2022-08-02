package com.example.app.viewtypeparser.parsers.parent

import android.view.View
import android.widget.LinearLayout
import com.example.app.viewtypeparser.processors.DimensionAttributeProcessor
import com.example.app.viewtypeparser.processors.GravityAttributeProcessor

class LinearLayoutLayoutParamsParser: ViewGroupMarginLayoutParamsParser() {

    init {
        attributeProcessorMap["layout_gravity"] = object : GravityAttributeProcessor<View>() {
            override fun handleValue(value: Int, view: View) {
                val lp = view.layoutParams
                if (lp is LinearLayout.LayoutParams)
                    lp.gravity = value
            }
        }
        attributeProcessorMap["layout_weight"] = object : DimensionAttributeProcessor<View>() {
            override fun handleValue(value: Float, view: View) {
                val lp = view.layoutParams
                if (lp is LinearLayout.LayoutParams)
                    lp.weight = value
            }
        }
    }
}