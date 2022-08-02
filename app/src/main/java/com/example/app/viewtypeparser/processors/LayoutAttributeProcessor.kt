package com.example.app.viewtypeparser.processors

import android.view.View
import android.view.ViewGroup

interface LayoutAttributeProcessor<V: View, LP: ViewGroup.LayoutParams> {
    fun process(rawValue: Any, view: V, layoutParams: LP)
}