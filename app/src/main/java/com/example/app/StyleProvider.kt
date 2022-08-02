package com.example.app

interface StyleProvider {

    fun getStyle(name: String): List<Pair<String, Any>>?
}