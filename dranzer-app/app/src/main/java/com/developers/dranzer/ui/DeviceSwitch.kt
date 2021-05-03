package com.developers.dranzer.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.developers.dranzer.R

class DeviceSwitch : SwitchCompat {

    companion object {

        private const val TRACK_COLOR = R.color.colorPrimaryLight
        private const val TRACK_STROKE_COLOR = R.color.colorPrimaryLight
        private const val TRACK_LABEL_COLOR = 0xFFFFFFFF.toInt()
        private val TRACK_STROKE_WIDTH = 2f.dp2Px.toInt()
        private val TRACK_LABEL_SIZE = 12f.sp2Px

        private const val THUMB_COLOR = R.color.colorPrimary
        private const val THUMB_LABEL_COLOR = 0xFFFFFFFF.toInt()
        private val THUMB_LABEL_SIZE = 12f.sp2Px

        private val TRACK_THUMB_PADDING = 3f.dp2Px.toInt()

        fun drawLabel(canvas: Canvas,
                      bounds: Rect,
                      paint: Paint,
                      text: CharSequence?) {
            text ?: return

            val tb = RectF()
            tb.right = paint.measureText(text, 0, text.length)
            tb.bottom = paint.descent() - paint.ascent()
            tb.left += bounds.centerX() - tb.centerX()
            tb.top += bounds.centerY() - tb.centerY() - paint.ascent()

            canvas.drawText(text.toString(), tb.left, tb.top, paint)
        }

        private inline val Float.sp2Px
            get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                this,
                Resources.getSystem().displayMetrics)

        private inline val Float.dp2Px
            get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                this,
                Resources.getSystem().displayMetrics)
    }

    private val trackLabelPaint = Paint().apply {
        isAntiAlias = true
        textSize = TRACK_LABEL_SIZE
        color = TRACK_LABEL_COLOR
    }

    private val thumbLabelPaint = Paint().apply {
        isAntiAlias = true
        textSize = THUMB_LABEL_SIZE
        color = THUMB_LABEL_COLOR
    }

    private val thumbLabel
        get () = if (isChecked) textOn else textOff

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        background = null
        trackDrawable = TrackDrawable()
        thumbDrawable = ThumbDrawable()
    }

    override fun onSizeChanged(w: Int,
                               h: Int,
                               oldw: Int,
                               oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        (trackDrawable as GradientDrawable).setSize(w, h)
        (thumbDrawable as GradientDrawable).setSize(w / 2, h)
    }

    inner class TrackDrawable : GradientDrawable() {

        private val textOffBounds = Rect()
        private val textOnBounds = Rect()

        init {
            setColor(ContextCompat.getColor(context, TRACK_COLOR))
            setStroke(TRACK_STROKE_WIDTH, ContextCompat.getColor(context, TRACK_STROKE_COLOR))
        }

        override fun onBoundsChange(r: Rect) {
            super.onBoundsChange(r)

            cornerRadius = r.height() / 2f

            textOffBounds.set(r)
            textOffBounds.right /= 2

            textOnBounds.set(textOffBounds)
            textOnBounds.offset(textOffBounds.right, 0)
        }

        override fun draw(canvas: Canvas) {
            super.draw(canvas)

            drawLabel(canvas, textOffBounds, trackLabelPaint, textOff)
            drawLabel(canvas, textOnBounds, trackLabelPaint, textOn)
            invalidate()
            requestLayout()
        }
    }

    inner class ThumbDrawable : GradientDrawable() {

        private val thumbLabelBounds = Rect()

        init {
            setColor(ContextCompat.getColor(context, THUMB_COLOR))
        }

        override fun onBoundsChange(r: Rect) {
            super.onBoundsChange(r)

            val padding = TRACK_THUMB_PADDING
            r.top += padding
            r.right -= padding
            r.bottom -= padding
            r.left += padding
            cornerRadius = r.height() / 2f
            thumbLabelBounds.set(r)
        }

        override fun draw(canvas: Canvas) {
            super.draw(canvas)

            drawLabel(canvas, thumbLabelBounds, thumbLabelPaint, thumbLabel)
            invalidate()
            requestLayout()
        }
    }
}