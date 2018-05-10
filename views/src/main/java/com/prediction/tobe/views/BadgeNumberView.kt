package com.prediction.tobe.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView


class BadgeNumberView(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    companion object {
        const val STATE_INACTIVE = 0
        const val STATE_ACTIVE = 1
        const val ORIENTATION_H = 0
        const val ORIENTATION_V = 1
        const val DIMENSION_UNSET = -1
        const val NUMBER_FISH = 25_700
    }

    private val formatter: NumberFormatter = NumberFormatter(getContext())

    private var imageSize: Int = DIMENSION_UNSET
    private var orientation: Int = 0
    private lateinit var badgeDrawable: Drawable
    private var textSize: Int = 0
    private var state: Int = 0
    private var activeColor: Int = 0
    private var inactiveColor: Int = 0
    private var numberValue: Long = 0

    private lateinit var txtBadgeNumber: TextView
    private lateinit var imgBadgeIcon: ImageView

    init {
        val ta: TypedArray = getContext().theme.obtainStyledAttributes(attrs, R.styleable.BadgeNumberView, 0, 0)
        try {
            imageSize = ta.getDimensionPixelSize(R.styleable.BadgeNumberView_imageSize, DIMENSION_UNSET)
            textSize = ta.getDimensionPixelSize(R.styleable.BadgeNumberView_textSize, DIMENSION_UNSET)
            state = ta.getInt(R.styleable.BadgeNumberView_state, STATE_INACTIVE)
            orientation = ta.getInt(R.styleable.BadgeNumberView_orientation, ORIENTATION_H)
            badgeDrawable = ta.getDrawable(R.styleable.BadgeNumberView_src) ?: getContext().getDrawable(R.drawable.ic_star_24)
            activeColor = ta.getColor(R.styleable.BadgeNumberView_activeColor, resources.getColor(R.color.badge_active))
            inactiveColor = ta.getColor(R.styleable.BadgeNumberView_inactiveColor, resources.getColor(R.color.badge_inactive))
            numberValue = ta.getInteger(R.styleable.BadgeNumberView_numberValue, NUMBER_FISH).toLong()
        } catch (e: Exception) {
        } finally {
            ta.recycle()
        }

        init()

        updateBadgeDrawable()
        updateState()
        updateImageSize()
        updateTextSize()
        updateNumberValue()
    }

    private fun init() {
        if (orientation == ORIENTATION_H) {
            View.inflate(context, R.layout.view_badge_numbers_h, this)
        } else {
            View.inflate(context, R.layout.view_badge_numbers_v, this)
        }

        txtBadgeNumber = this.findViewById(R.id.txtBadgeNumber) as TextView
        imgBadgeIcon = this.findViewById(R.id.imgBadgeIcon) as ImageView
    }

    private fun updateState() {
        val color = if (state == STATE_ACTIVE) activeColor else inactiveColor

        imgBadgeIcon.setColorFilter(color)
        txtBadgeNumber.setTextColor(color)
    }

    private fun updateImageSize() {
        if (imageSize != DIMENSION_UNSET && imageSize > 0) {
            imgBadgeIcon.layoutParams.height = imageSize
            imgBadgeIcon.layoutParams.width = imageSize
        }
    }

    private fun updateTextSize() {
        if (textSize != DIMENSION_UNSET && textSize > 0) {
            txtBadgeNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        }
    }

    private fun updateNumberValue() {
        txtBadgeNumber.text = formatter.formatNumber(numberValue)
    }

    private fun updateActiveColor() {
        updateState()
    }

    private fun updateBadgeDrawable() {
        imgBadgeIcon.setImageDrawable(badgeDrawable)
    }

    fun getImageSize(): Int {
        return imageSize
    }

    fun setImageSize(imageSize: Int) {
        this.imageSize = imageSize
        updateImageSize()
    }

    fun getTextSize(): Int {
        return textSize
    }

    fun setTextSize(textSize: Int) {
        this.textSize = textSize
        updateTextSize()
    }

    fun getState(): Int {
        return state
    }

    fun setState(state: Int) {
        this.state = state
        updateState()
    }

    fun getNumberValue(): Long {
        return numberValue
    }

    fun setNumberValue(numberValue: Long) {
        this.numberValue = numberValue
        updateNumberValue()
    }

    fun getBadgeDrawable(): Drawable {
        return badgeDrawable
    }

    fun setActiveColor(color: Int) {
        this.activeColor = color
        updateActiveColor()
    }

    fun setInactiveColor(color: Int) {
        this.inactiveColor = color
        updateActiveColor()
    }

    fun setBadgeDrawable(badgeDrawable: Drawable) {
        this.badgeDrawable = badgeDrawable
        updateBadgeDrawable()
    }

    class NumberFormatter(ctx: Context) {
        private val signThousand: String = ctx.getString(R.string.sign_thousand)
        private val signMillion: String = ctx.getString(R.string.sign_million)
        private val signBillion: String = ctx.getString(R.string.sign_billion)
        private val signTrillion: String = ctx.getString(R.string.sign_trillion)

        fun formatNumber(number: Long): String {
            val grade = (number.toString().length - 1) / 3
            val topGradeNumber = (number / Math.pow(10.0, (3 * grade).toDouble())).toInt()

            var result = topGradeNumber.toString()
            if (result.length <= 2 && grade > 0) {
                val subTopNumber = (number % Math.pow(10.0, (3 * grade).toDouble()) / Math.pow(10.0, (3 * grade - 1).toDouble())).toInt()
                if (subTopNumber > 0) {
                    result += "." + subTopNumber.toString()
                }
            }

            val gradeSign = when (grade) {
                1 -> signThousand
                2 -> signMillion
                3 -> signBillion
                4 -> signTrillion
                else -> ""
            }
            result += gradeSign

            return result
        }
    }
}