package com.example.app.parsers.parent

import android.view.View
import android.widget.FrameLayout
import com.example.app.processors.GravityAttributeProcessor

class FrameLayoutLayoutParamsParser: ViewGroupMarginLayoutParamsParser() {

    init {
        attributeProcessorMap["layout_gravity"] = object : GravityAttributeProcessor<View>() {
            override fun handleValue(value: Int, view: View) {
                val lp = view.layoutParams
                if (lp is FrameLayout.LayoutParams) {
                    lp.gravity = value
                }
            }
        }
    }
}