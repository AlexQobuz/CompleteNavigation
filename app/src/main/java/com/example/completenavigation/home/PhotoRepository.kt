package com.example.completenavigation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class PhotoRepository {

    private val retrofitBuilder: HomeInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HomeInterface::class.java)

    /**
     * Fonction qui utilise retrofit pour le call API
     */
    fun getAllPhotos(): LiveData<List<Photo>> {
            val photoListLiveData = MutableLiveData<List<Photo>>()

            val retrofitData = retrofitBuilder.getAllPhotos()

            retrofitData.enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    val responseBody = response.body()!!
                    photoListLiveData.value = responseBody
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("Activity main","Les photos ne peuvent pas être afficher suite à un problème !"+t.message )
                }

            })

        return photoListLiveData

    }

}