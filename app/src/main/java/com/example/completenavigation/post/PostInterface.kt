package com.example.completenavigation.post

import retrofit2.Call
import retrofit2.http.GET

interface PostInterface {

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

}