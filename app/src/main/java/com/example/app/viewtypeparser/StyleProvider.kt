package com.example.app.viewtypeparser

interface StyleProvider {

    fun getStyle(name: String): List<Pair<String, Any>>?
}