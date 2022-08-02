package com.example.app.parsers

import android.content.Context
import android.widget.FrameLayout

class FrameLayoutParser<T : FrameLayout> : ViewGroupParser<T>() {
//    init {
//        val frameLayoutAttributeProcessorMap = mutableMapOf<String, AttributeProcessor<View>>()
//        frameLayoutAttributeProcessorMap.putAll(parentParserMap[ViewGroup.LayoutParams::class.java]!!)
//        frameLayoutAttributeProcessorMap["layout_gravity"] = object : GravityAttributeProcessor<View>() {
//            override fun handleValue(value: Int, view: View) {
//                val lp = view.layoutParams
//                if (lp is FrameLayout.LayoutParams)
//                    lp.gravity = value
//            }
//        }
//        parentParserMap[FrameLayout.LayoutParams::class.java] = frameLayoutAttributeProcessorMap
//    }

    @Suppress("UNCHECKED_CAST")
    override fun createView(context: Context): T {
        return FrameLayout(context) as T
    }
}