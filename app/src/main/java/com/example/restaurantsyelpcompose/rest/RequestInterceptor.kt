package com.example.restaurantsyelpcompose.rest

import com.example.restaurantsyelpcompose.ViewModel.Utils.AUTH_HEADER
import com.example.restaurantsyelpcompose.ViewModel.Utils.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(AUTH_HEADER, AUTH_TOKEN)
            .build()

        return chain.proceed(request)
    }
}