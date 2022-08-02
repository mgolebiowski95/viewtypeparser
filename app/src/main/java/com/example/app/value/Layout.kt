package com.example.app.value

data class Layout(val type: String, val attributeList: List<Pair<String, Any>>) {
    constructor(attributeList: List<Pair<String, Any>>): this(attributeList.toMap().getValue("type").toString(), attributeList)
}