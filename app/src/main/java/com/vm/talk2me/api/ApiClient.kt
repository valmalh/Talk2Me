package com.vm.talk2me.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by VanManh on 02-Sep-17.
 */

object ApiClient {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://sandbox.api.simsimi.com/"

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
}
