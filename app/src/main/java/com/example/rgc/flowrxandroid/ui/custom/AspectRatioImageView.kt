package com.example.rgc.flowrxandroid.ui.custom

import android.content.Context
import android.util.AttributeSet
import com.example.rgc.flowrxandroid.R

class AspectRatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    var ratio: Float = 1.0f

    init {
        val attrs = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
        ratio = attrs.getFloat(R.styleable.AspectRatioImageView_ratio, 1.0f)
        attrs.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var width = measuredWidth
        var height = measuredHeight

        if (width == 0 && height == 0) {
            return
        }

        if(width > 0 ) {
            height = (width * ratio).toInt()
        } else if (height > 0) {
            width = (height / ratio).toInt()
        }

        setMeasuredDimension(width, height)
    }

}