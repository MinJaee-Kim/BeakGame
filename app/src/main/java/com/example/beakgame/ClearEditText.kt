package com.example.beakgame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

class ClearEditText : AppCompatEditText, View.OnClickListener, View.OnFocusChangeListener, TextWatcher {

    lateinit var clearDrawable: Drawable
    private lateinit var onFocusChangeListener1: OnFocusChangeListener
    private lateinit var onTouchListener1: OnTouchListener

    constructor(
        context: Context,
    ) : super(context) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
    ) : super(context, attrs) {
        init()
    }


    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        val tempDrawable = ContextCompat.getDrawable(context, com.airbnb.lottie.R.drawable.abc_ic_clear_material)
        clearDrawable = tempDrawable?.let { DrawableCompat.wrap(it) }!!
        DrawableCompat.setTintList(clearDrawable,hintTextColors)
        clearDrawable.setBounds(0, 0, clearDrawable.intrinsicWidth, clearDrawable.intrinsicHeight)

        setClearIconVisible(false)

        addTextChangedListener(this)
        super.setOnTouchListener(this.onTouchListener1)
        super.setOnFocusChangeListener(this.onFocusChangeListener1)
    }

    override fun setOnFocusChangeListener(l: OnFocusChangeListener?) {
        if (l != null) {
            this.onFocusChangeListener1 = l
        }
    }

    override fun setOnTouchListener(l: OnTouchListener?) {
        if (l != null) {
            this.onTouchListener1 = l
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        if (isFocused) {
            if (text != null) {
                setClearIconVisible(text.isNotEmpty())
            }
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            setClearIconVisible(text!!.isNotEmpty())
        } else {
            setClearIconVisible(false)
        }

        if (onFocusChangeListener != null) {

        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x:Int = MotionEvent.AXIS_X

        if (clearDrawable.isVisible && x > width - paddingRight - clearDrawable.intrinsicWidth) {
            if (event?.action == MotionEvent.ACTION_UP) {
                setError(null)
                setText(null)
            }
            return true
        }

        return onTouchListener1.onTouch(View(context), event)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("Not yet implemented")
    }

    override fun afterTextChanged(s: Editable?) {
        TODO("Not yet implemented")
    }



    private fun setClearIconVisible(visible: Boolean) {
        clearDrawable.setVisible(visible, false)
        if (visible) {
            setCompoundDrawables(null, null, clearDrawable, null)
        } else {
            setCompoundDrawables(null, null, null, null)
        }
    }
}