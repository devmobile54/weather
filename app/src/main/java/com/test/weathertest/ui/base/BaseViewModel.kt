package com.test.weathertest.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<N : BaseNavigator> : ViewModel() {

    private var mNavigator: N? = null
    private var mCompositeDisposable: CompositeDisposable? = null

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }

    fun getNavigator(): N? {
        return mNavigator
    }

    fun getCompositeDisposable(): CompositeDisposable? {
        return mCompositeDisposable
    }

    public override fun onCleared() {
        getCompositeDisposable()?.clear()
        super.onCleared()
    }
}