package com.example.app.viewtypeparser.parsers

import android.view.ViewGroup
import com.example.app.viewtypeparser.JsonLayoutInflaterProvider
import com.example.app.viewtypeparser.processors.AttributeProcessor
import com.example.app.viewtypeparser.value.Layout

abstract class ViewGroupParser<V : ViewGroup> : ViewParser<V>() {

    init {
        attributeProcessorMap["children"] = object : AttributeProcessor<V> {
            override fun process(rawValue: Any, view: V) {
                if (rawValue is List<*>) {
                    rawValue.forEach { it ->
                        @Suppress("UNCHECKED_CAST")
                        val attributes = it as Map<String, Any>
                        val layout = Layout(attributes.getValue("type").toString(), attributes.map { Pair(it.key, it.value) })
                        val context = view.context
                        val jsonLayoutInflater = (context.applicationContext as JsonLayoutInflaterProvider).jsonLayoutInflater
                        jsonLayoutInflater.inflate(context, layout, view, true)
                    }
                }
            }
        }
    }
}