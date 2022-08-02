package com.example.app

interface FunctionProvider {

    fun getFunction(name: String): Accessor?

    interface Accessor {
        fun get(): Any
    }
}