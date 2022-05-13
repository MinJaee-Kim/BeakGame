package com.example.beakgame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.lang.Exception

class CustomEditText : AppCompatEditText, TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {
    private lateinit var clearDrawable: Drawable
    private lateinit var onfocuschangeListener: OnFocusChangeListener
    private lateinit var ontouchListener: OnTouchListener
    private var count:Int = 0

    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context) : super(context) {init()}
    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {init()}
    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {init()}

    override fun setOnFocusChangeListener(l: OnFocusChangeListener?) {
        onfocuschangeListener = l!!
        count = 1
    }

    override fun setOnTouchListener(l: OnTouchListener?) {
        ontouchListener = l!!
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (hasFocus && text != null) setClearIconVisivle(text!!.isNotEmpty())
        else setClearIconVisivle(false)

        if (count == 1) onfocuschangeListener.onFocusChange(view, hasFocus)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        try {

        val x = event.x.toInt()
        if (clearDrawable.isVisible && x > width - paddingRight - clearDrawable.intrinsicWidth) {
            if (event.action == MotionEvent.ACTION_UP){
                error = null
                text = null
            }
            return true
        }

        return ontouchListener.onTouch(v, event)
        } catch (i:Exception) {
            return false
        }
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (isFocused) setClearIconVisivle(text.isNotEmpty())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//        TODO("Not yet implemented")
    }

    override fun afterTextChanged(p0: Editable?) {
//        TODO("Not yet implemented")
    }

    private fun setClearIconVisivle(visible:Boolean){
        clearDrawable.setVisible(visible, false)
        setCompoundDrawables(null, null, if (visible) clearDrawable else null, null)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    private fun init(){
        clearDrawable = DrawableCompat.wrap(
            (ResourcesCompat.getDrawable(resources, androidx.appcompat.R.drawable.abc_ic_clear_material,
                null) as Drawable))

        DrawableCompat.setTintList(clearDrawable, hintTextColors)
        clearDrawable.setBounds(0,0,clearDrawable.intrinsicWidth, clearDrawable.intrinsicHeight)

        clearDrawable.colorFilter = PorterDuffColorFilter(context.getColor(R.color.purple_700), PorterDuff.Mode.SRC_IN)

        setClearIconVisivle(false)
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)
    }

}