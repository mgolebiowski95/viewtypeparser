package com.example.app.processors

import android.view.View

interface AttributeProcessor<in T : View> {
    fun process(rawValue: Any, view: T)
}