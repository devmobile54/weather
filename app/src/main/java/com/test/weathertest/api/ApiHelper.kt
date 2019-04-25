package com.test.weathertest.api

import com.google.gson.Gson
import com.test.weathertest.ui.basemodel.ApiError
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

class ApiHelper {

    interface OnApiCallbackListener<T> {
        fun onSubscribe(d: Disposable)
        fun onSuccess(t: T)
        fun onError(apiError: ApiError)
    }

    inner class CallBackWithGenericListener<T>(private val onApiObserverCallbackListener: OnApiCallbackListener<T>) :
        SingleObserver<Response<T>> {

        override fun onSuccess(t: Response<T>) {
            if (t.isSuccessful) {
                if (t.code() == 200) {
                    try {
                        t.body()?.let {
                            onApiObserverCallbackListener.onSuccess(it)
                        } ?: run {
                            val apiError = Gson().fromJson(t.errorBody()?.string(), ApiError::class.java)
                            onApiObserverCallbackListener.onError(apiError)
                        }
                    }catch (e : Exception){
                        onApiObserverCallbackListener.onError(convertErrorBody(0, "Throwable" + e.message))
                    }
                } else {
                    try {
                        val apiError = Gson().fromJson(Gson().toJson(t.body()), ApiError::class.java)
                        onApiObserverCallbackListener.onError(apiError)
                    }catch (e : Exception){
                        onApiObserverCallbackListener.onError(convertErrorBody(0, "Throwable" + e.message))
                    }
                }
            } else {
                try {
                    val apiError = Gson().fromJson(t.errorBody()?.string(), ApiError::class.java)
                    onApiObserverCallbackListener.onError(apiError)
                }catch (e : Exception){
                    onApiObserverCallbackListener.onError(convertErrorBody(0, "Throwable" + e.message))
                }

            }
        }

        override fun onSubscribe(d: Disposable) {
            onApiObserverCallbackListener.onSubscribe(d)
        }

        override fun onError(throwable: Throwable) {
            if (throwable is HttpException) {
                val response = throwable.response() as Response<*>
                val apiError = Gson().fromJson(response.errorBody()?.string(), ApiError::class.java)
                if (apiError != null) {
                    if (apiError.statusCode == 400) {
                        onApiObserverCallbackListener.onError(apiError)
                    } else {
                        onApiObserverCallbackListener.onError(apiError)
                    }
                } else {
                    onApiObserverCallbackListener.onError(convertErrorBody(apiError?.statusCode!!, "Not success = " + apiError.message))
                }
            } else {
                onApiObserverCallbackListener.onError(convertErrorBody(0, "Throwable" + throwable.message))
            }
        }
    }

    fun convertErrorBody(code: Int, message: String): ApiError {
        return ApiError(code, message)
    }

}