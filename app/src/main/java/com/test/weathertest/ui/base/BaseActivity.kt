package com.test.weathertest.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

abstract class BaseActivity<N : BaseNavigator, out V : BaseViewModel<N>> : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null
    private var baseDialogProgress: BaseDialogProgress? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    fun onFragmentAttached() {}

    fun showProgressDialog() {
        baseDialogProgress?.dismiss()

        baseDialogProgress = BaseDialogProgress(this)
        baseDialogProgress?.show()
    }

    fun dismissProgressDialog() {
        baseDialogProgress?.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayoutId())
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected open fun setBindingView() {}
    protected open fun setBindingData() {}
    protected open fun subscribeData() {}
}