package com.example.app.viewtypeparser

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.app.viewtypeparser.parsers.*
import com.example.app.viewtypeparser.parsers.parent.*
import com.example.app.viewtypeparser.value.Layout

class LayoutInflater {
    private val viewParserMap = mutableMapOf<String, ViewTypeParser<out View>>()
    private val parentParserMap = mutableMapOf<Class<out ViewGroup.LayoutParams>, ParentParser>()

    init {
        viewParserMap["View"] = ViewParser()
        viewParserMap["TextView"] = TextViewParser()
        viewParserMap["ImageView"] = ImageViewParser()
        viewParserMap["FrameLayout"] = FrameLayoutParser()
        viewParserMap["LinearLayout"] = LinearLayoutParser()
        viewParserMap["ConstraintLayout"] = ConstraintLayoutParser()

        parentParserMap[ViewGroup.LayoutParams::class.java] = ViewGroupLayoutParamsParser()
        parentParserMap[FrameLayout.LayoutParams::class.java] = FrameLayoutLayoutParamsParser()
        parentParserMap[LinearLayout.LayoutParams::class.java] = LinearLayoutLayoutParamsParser()
        parentParserMap[RelativeLayout.LayoutParams::class.java] = RelativeLayoutLayoutParamsParser()
        parentParserMap[ConstraintLayout.LayoutParams::class.java] = ConstraintLayoutLayoutParamsParser()
    }

    fun inflate(context: Context, layout: Layout, root: ViewGroup?, attachToRoot: Boolean = false): View? {
        val view = findParserByType<View>(layout.type)?.createView(context, layout, root)
        if(view != null && root != null) {
            val parentParser = parentParserMap[view.layoutParams::class.java]
            parentParser?.apply(layout.attributeList, view)
            if(attachToRoot)
                root.addView(view)
        }
        return view
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : View> findParserByType(type: String): ViewTypeParser<T>? {
        return viewParserMap[type] as? ViewTypeParser<T>
    }
}