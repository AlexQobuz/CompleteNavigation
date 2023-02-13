package com.example.completenavigation.home

import com.example.completenavigation.post.Post
import com.example.completenavigation.user.User
import retrofit2.Call
import retrofit2.http.GET

interface HomeInterface {

    @GET("photos")
    fun getAllPhotos(): Call<List<Photo>>

    @GET("users")
    fun getAllUsers(): Call<List<User>>

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

}