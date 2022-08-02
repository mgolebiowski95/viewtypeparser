package com.example.app.viewtypeparser.parsers

import android.content.Context
import android.content.res.XmlResourceParser
import android.view.View
import android.view.ViewGroup
import com.example.app.R
import com.example.app.viewtypeparser.processors.AttributeProcessor
import com.example.app.viewtypeparser.value.Layout
import org.xmlpull.v1.XmlPullParser

abstract class ViewTypeParser<T : View> {
    companion object {
        var debug = true
        private var parser: XmlResourceParser? = null
    }

    val attributeProcessorMap = mutableMapOf<String, AttributeProcessor<T>>()

    fun createView(context: Context, layout: Layout, parent: ViewGroup?): T {
        val view = createView(context)
        view.layoutParams = if (parent != null)
            generateDefaultLayoutParams(parent)
        else
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        apply(layout.attributeList, view)
        return view
    }

    @Suppress("UNCHECKED_CAST")
    fun apply(attributeList: List<Pair<String, Any>>, view: T) {
        attributeList.forEach {
            handleAttribute(it, view)
        }
    }

   private fun handleAttribute(rawValue: Pair<String, Any>, view: T) {
       val attributeProcessor = attributeProcessorMap[rawValue.first]
       attributeProcessor?.let {
           if(debug)
               println("ViewTypeParser.handleAttribute: rawValue=$rawValue, view=${view::class.java.simpleName}")
           it.process(rawValue.second, view)
       }
    }

    @Suppress("UNCHECKED_CAST")
    protected abstract fun createView(context: Context): T

    private fun generateDefaultLayoutParams(parent: ViewGroup): ViewGroup.LayoutParams {
        if (null == parser) {
            synchronized(ViewTypeParser::class.java) {
                if (null == parser) {
                    parser = parent.resources.getLayout(R.layout.layout_params)
                    try {
                        while (parser?.nextToken() != XmlPullParser.START_TAG) {
                            // Skip everything until the view tag.
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return parent.generateLayoutParams(parser)
    }
}