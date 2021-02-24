package com.nannaapp.kotlin.Retrofit

import com.nannaapp.kotlin.Model.ListItem
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("photos")
    fun getJsonData():
            Call<ArrayList<ListItem>>


    companion object {
        fun create(): RetrofitService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            return retrofit.create(RetrofitService::class.java)
        }
    }
}