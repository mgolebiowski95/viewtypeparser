package com.example.app.parsers.parent

import android.view.View
import com.example.app.processors.AttributeProcessor

abstract class ParentParser {
    companion object {
        var debug = true
    }

    val attributeProcessorMap = mutableMapOf<String, AttributeProcessor<View>>()

    fun apply(attributeList: List<Pair<String, Any>>, view: View) {
        attributeList.forEach {
            handleAttribute(it, view)
        }
    }

    private fun handleAttribute(rawValue: Pair<String, Any>, view: View) {
        val attributeProcessor = attributeProcessorMap[rawValue.first]
        attributeProcessor?.let {
            if(debug)
                println("ParentParser.handleAttribute: rawValue=$rawValue, view=${view::class.java.simpleName}")
            it.process(rawValue.second, view)
        }
    }
}