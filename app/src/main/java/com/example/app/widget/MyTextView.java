package com.example.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MyTextView extends AppCompatTextView {
    private CharSequence originalText = "";

    public MyTextView(Context context) {
//        onPreCreate(context, null);
        super(context);
        onCreate(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
//        onPreCreate(context, attrs);
        super(context, attrs);
        onCreate(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
//        onPreCreate(context, attrs);
        super(context, attrs, defStyleAttr);
        onCreate(context, attrs);
    }

    private void onPreCreate(Context context, AttributeSet attrs) {
    }

    private void onCreate(Context context, AttributeSet attrs) {
        int[] set = {android.R.attr.text};
        TypedArray a = context.obtainStyledAttributes(attrs, set);
        originalText = a.getText(0);
        if(originalText == null)
            originalText = "";
    }

    @Override
    public CharSequence getText() {
        return originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        super.setText(text, type);
    }
}
