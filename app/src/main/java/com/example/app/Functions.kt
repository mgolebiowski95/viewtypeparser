package com.example.app

class Functions: FunctionProvider {
    private val functions = mutableMapOf<String, FunctionProvider.Accessor>()

    init {
        functions["city"] = object : FunctionProvider.Accessor {
            override fun get(): Any {
                return "Poland"
            }
        }
    }

    override fun getFunction(name: String): FunctionProvider.Accessor? {
        return functions[name]
    }
}