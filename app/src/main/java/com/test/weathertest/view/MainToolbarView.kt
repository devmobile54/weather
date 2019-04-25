package com.test.weathertest.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.test.weathertest.R
import com.test.weathertest.interfaces.OnToolbarClickListener
import kotlinx.android.synthetic.main.main_toolbar.view.*


class MainToolbarView : FrameLayout, View.OnClickListener {

    private var onToolbarClickListener: OnToolbarClickListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initInflate()
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initInflate()
        init(context, attrs)
    }

    private fun initInflate() {
        View.inflate(context, R.layout.main_toolbar, this)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        celsius.setOnClickListener(this)
        fahrenheit.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view) {
            celsius -> { this.onToolbarClickListener?.onToolbarClickListener(view) }
            fahrenheit -> { this.onToolbarClickListener?.onToolbarClickListener(view) }
        }
    }


    fun setListener(onToolbarClickListener: OnToolbarClickListener) {
        this.onToolbarClickListener = onToolbarClickListener
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }
}