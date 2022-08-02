package com.example.app.parsers.parent

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.app.processors.AttributeProcessor
import com.example.app.processors.DimensionAttributeProcessor
import com.example.app.processors.IdAttributeProcessor

class ConstraintLayoutLayoutParamsParser : ViewGroupMarginLayoutParamsParser() {

    init {
        attributeProcessorMap["layout_constraintWidth_percent"] = object : DimensionAttributeProcessor<View>() {
            override fun handleValue(value: Float, view: View) {
                val lp = view.layoutParams
                if (lp is ConstraintLayout.LayoutParams)
                    lp.matchConstraintPercentWidth = value
            }
        }
        attributeProcessorMap["layout_constraintAnchor_toAnchorOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
                            lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                            lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
                            lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintHorizontalAnchor_toHorizontalAnchorOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
                            lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintVerticalAnchor_toVerticalAnchorOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                            lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintLeft_toLeftOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
                        } else {
                            val idAttributeProcessor = object : IdAttributeProcessor<View>() {
                                override fun handleValue(value: Int, view: View) {
                                    lp.leftToLeft = value
                                }
                            }
                            idAttributeProcessor.process(rawValue, view)
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintLeft_toRightOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.leftToRight = ConstraintLayout.LayoutParams.PARENT_ID
                        } else {
                            val idAttributeProcessor = object : IdAttributeProcessor<View>() {
                                override fun handleValue(value: Int, view: View) {
                                    lp.leftToRight = value
                                }
                            }
                            idAttributeProcessor.process(rawValue, view)
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintTop_toTopOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                        } else {
                            val idAttributeProcessor = object : IdAttributeProcessor<View>() {
                                override fun handleValue(value: Int, view: View) {
                                    lp.topToTop = value
                                }
                            }
                            idAttributeProcessor.process(rawValue, view)
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintTop_toBottomOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.topToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                        } else {
                            val idAttributeProcessor = object : IdAttributeProcessor<View>() {
                                override fun handleValue(value: Int, view: View) {
                                    lp.topToBottom = value
                                }
                            }
                            idAttributeProcessor.process(rawValue, view)
                        }

                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintRight_toRightOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
                        } else {
                            val idAttributeProcessor = object : IdAttributeProcessor<View>() {
                                override fun handleValue(value: Int, view: View) {
                                    lp.rightToRight = value
                                }
                            }
                            idAttributeProcessor.process(rawValue, view)
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintRight_toLeftOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.rightToLeft = ConstraintLayout.LayoutParams.PARENT_ID
                        } else {
                            val idAttributeProcessor = object : IdAttributeProcessor<View>() {
                                override fun handleValue(value: Int, view: View) {
                                    lp.rightToLeft = value
                                }
                            }
                            idAttributeProcessor.process(rawValue, view)
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintBottom_toBottomOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                        } else {
                            val idAttributeProcessor = object : IdAttributeProcessor<View>() {
                                override fun handleValue(value: Int, view: View) {
                                    lp.bottomToBottom = value
                                }
                            }
                            idAttributeProcessor.process(rawValue, view)
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintBottom_toTopOf"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        if (rawValue == "parent") {
                            lp.bottomToTop = ConstraintLayout.LayoutParams.PARENT_ID
                        } else {
                            val idAttributeProcessor = object : IdAttributeProcessor<View>() {
                                override fun handleValue(value: Int, view: View) {
                                    lp.bottomToTop = value
                                }
                            }
                            idAttributeProcessor.process(rawValue, view)
                        }
                    }
                }
            }
        }
        attributeProcessorMap["layout_constraintHorizontal_bias"] = object : DimensionAttributeProcessor<View>() {
            override fun handleValue(value: Float, view: View) {
                val lp = view.layoutParams
                if (lp is ConstraintLayout.LayoutParams)
                    lp.horizontalBias = value
            }
        }
        attributeProcessorMap["layout_constraintVertical_bias"] = object : DimensionAttributeProcessor<View>() {
            override fun handleValue(value: Float, view: View) {
                val lp = view.layoutParams
                if (lp is ConstraintLayout.LayoutParams)
                    lp.verticalBias = value
            }
        }
        attributeProcessorMap["layout_constraintDimensionRatio"] = object : AttributeProcessor<View> {
            override fun process(rawValue: Any, view: View) {
                if (rawValue is String) {
                    val lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        lp.dimensionRatio = rawValue
                    }
                }
            }
        }
    }
}