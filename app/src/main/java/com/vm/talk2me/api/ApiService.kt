package com.vm.talk2me.api

import com.vm.talk2me.model.MessageResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by VanManh on 02-Sep-17.
 */

interface ApiService {
    @GET("request.p")
    fun getMesage(@Query("key") apiKey: String, @Query("lc") lang: String, @Query("ft") badWordDiscriminator: Double?, @Query("text") mess: String): Call<MessageResponse>
}
