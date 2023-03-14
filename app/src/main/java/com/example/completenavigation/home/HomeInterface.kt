package com.example.completenavigation.home

import retrofit2.Call
import retrofit2.http.GET

interface HomeInterface {

    @GET("photos")
    fun getAllPhotos(): Call<List<Photo>>

}