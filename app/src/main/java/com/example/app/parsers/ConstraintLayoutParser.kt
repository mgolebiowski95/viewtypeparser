package com.example.app.parsers

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout

class ConstraintLayoutParser<T : ConstraintLayout> : ViewGroupParser<T>() {

    @Suppress("UNCHECKED_CAST")
    override fun createView(context: Context): T {
        return ConstraintLayout(context) as T
    }
}