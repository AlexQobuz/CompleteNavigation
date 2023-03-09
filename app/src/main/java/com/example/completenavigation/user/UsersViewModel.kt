package com.example.completenavigation.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Je définis ici les données
 * que je veux conserver et gérer dans mon ViewModel
 */

class UsersViewModel : ViewModel() {

    private lateinit var retrofitBuilder: UserInterface

    val userListLiveData = MutableLiveData<List<User>>()

    /**
     * J'implémente ma fonction qui récupère
     * les données pour les mettre à jour.
     */
    /**
     * Fonction qui utilise retrofit pour le call API
     */
     fun getUsers() {
        if (!::retrofitBuilder.isInitialized){
            retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserInterface::class.java)
        }

        val retrofitData = retrofitBuilder.getAllUsers()

        retrofitData.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val responseBody = response.body()!!
                userListLiveData.value = responseBody
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Activity main","Les users ne peuvent pas être afficher suite à un problème !"+t.message )
            }

        })
    }

}