package com.example.app.base

import android.app.Application
import com.example.app.FunctionProvider
import com.example.app.StyleProvider

class MyApplication : Application(), JsonLayoutInflaterProvider, StyleProvider, FunctionProvider {

    private lateinit var _jsonLayoutInflater: LayoutInflater

    override val jsonLayoutInflater: LayoutInflater
        get() = _jsonLayoutInflater

    override fun onCreate() {
        super.onCreate()
        _jsonLayoutInflater = LayoutInflater()

        val styleProvider = MyStyleProvider()
        styleProvider.loadFromAssets(assets, "style.json")
    }

    private val styles = mutableMapOf<String, List<Pair<String, Any>>>()
    private val functions = mutableMapOf<String, FunctionProvider.Accessor>()

    init {
        styles["title"] = listOf(
                Pair("textSize", "24sp"),
                Pair("textColor", "#ff00ff00")
        )

        functions["placeholder"] = object : FunctionProvider.Accessor {
            override fun get(): Any {
                return "placeholder"
            }
        }
    }

    override fun getStyle(name: String): List<Pair<String, Any>>? {
        return styles[name]
    }


    override fun getFunction(name: String): FunctionProvider.Accessor? {
        return functions[name]
    }
}