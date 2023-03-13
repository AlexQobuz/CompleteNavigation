package com.example.completenavigation.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class UserRepository {

    private val retrofitBuilder: UserInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserInterface::class.java)

    /**
     * Fonction qui utilise retrofit pour le call API
     */
    fun getAllUsers(): LiveData<List<User>> {
        val userListLiveData = MutableLiveData<List<User>>()

        val retrofitData = retrofitBuilder.getAllUsers()

        retrofitData.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val responseBody = response.body()!!
                userListLiveData.value = responseBody
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("UserRepository", "Impossible de récupérer la liste des utilisateurs : ${t.message}")
            }

        })

        return userListLiveData
    }

}
