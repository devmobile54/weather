package com.test.weathertest.api

import okhttp3.Interceptor
import okhttp3.Response

class SignedRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder().apply {
            header("content-type", "application/json")
        }

        return chain.proceed(request.build())
    }


}