package com.example.completenavigation.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class PostRepository {

    private val retrofitBuilder: PostInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostInterface::class.java)

    /**
     * Fonction qui utilise retrofit pour le call API
     */
     fun getAllPosts(): LiveData<List<Post>> {
        val postListLiveData = MutableLiveData<List<Post>>()

        val retrofitData = retrofitBuilder.getAllPosts()

        retrofitData.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val responseBody = response.body()!!

                postListLiveData.value = responseBody
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("Activity main","Les posts ne peuvent pas être afficher suite à un problème !"+t.message )
            }

        })

        return postListLiveData
    }

}