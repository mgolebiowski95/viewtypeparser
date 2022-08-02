package com.example.app.viewtypeparser

interface FunctionProvider {

    fun getFunction(name: String): Accessor?

    interface Accessor {
        fun get(): Any
    }
}