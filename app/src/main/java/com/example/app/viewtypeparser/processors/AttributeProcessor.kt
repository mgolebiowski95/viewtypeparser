package com.example.app.viewtypeparser.processors

import android.view.View

interface AttributeProcessor<in T : View> {
    fun process(rawValue: Any, view: T)
}