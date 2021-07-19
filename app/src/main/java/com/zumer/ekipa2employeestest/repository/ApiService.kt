package com.zumer.ekipa2employeestest.repository

import com.zumer.ekipa2employeestest.model.SearchResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/")
    fun getHitsResults(@Query("q") q: String, @Query("num") num: Int, @Query("api_key") api_key: String, @Query("device") device: String): Call<SearchResponse>

//    companion object {
//        var BASE_URL: String = "https://serpapi.com/"
//
//        fun create() : ApiService {
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build()
//            return retrofit.create(ApiService::class.java)
//        }
//    }
}