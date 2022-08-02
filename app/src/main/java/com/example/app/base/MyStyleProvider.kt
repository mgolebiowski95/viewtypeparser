package com.example.app.base

import android.content.res.AssetManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

class MyStyleProvider() {
    private val styleMap = mutableMapOf<String, List<Pair<String, Any>>>()

    fun loadFromAssets(assets: AssetManager, path: String) {
        val gson = GsonBuilder().create()
        val type = object : TypeToken<Map<String, Any>>() {}.type
        val attributeMap = gson.fromJson<Map<String, Any>>(JsonReader(InputStreamReader(assets.open(path))), type)
        attributeMap.forEach{
//            styleMap[it.key] = attributeMap[it.value]
        }
        println()
    }
}