package com.test.weathertest.api

import android.util.Log
import com.google.gson.Gson
import com.test.weathertest.BuildConfig.BASE_URL
import com.test.weathertest.BuildConfig.DEBUG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object {
        fun <S> createService(serviceClass: Class<S>, baseURL: String? = BASE_URL): S {
            val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("API HttpLog", message) })
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(SignedRequestInterceptor())
                if (DEBUG) addInterceptor(logging)
                followRedirects(true)
                followSslRedirects(true)
                retryOnConnectionFailure(true)
                cache(null)
                readTimeout(60, TimeUnit.SECONDS)
                connectTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
            }
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient.build())
                .build().create(serviceClass)
        }
    }

}