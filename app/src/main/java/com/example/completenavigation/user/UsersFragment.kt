package com.example.completenavigation.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.completenavigation.R
import com.example.completenavigation.user.UserDetailActivity.Companion.EXTRA_USER
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class UserFragment : Fragment() {

    lateinit var myAdapter: UserAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerViewUsers: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewUsers = view.findViewById(R.id.recyclerview_users)
        recyclerViewUsers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerViewUsers.layoutManager = linearLayoutManager

        // Ici je réutilise la fonction
        // lambda pour l'événement au click
        myAdapter = UserAdapter(requireContext(), onClick = { user ->
            Log.d("UserFragment", "Salut")
            val intent = Intent(requireContext(), UserDetailActivity::class.java)
            intent.putExtra(EXTRA_USER, user)
            requireActivity().startActivity(intent)
        })
        recyclerViewUsers.adapter = myAdapter
        getUsers()

    }

    private fun getUsers() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserInterface::class.java)

        val retrofitData = retrofitBuilder.getAllUsers()

        retrofitData.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val responseBody = response.body()!!

                myAdapter.setItems(responseBody)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Activity main","Les users ne peuvent pas être afficher suite à un problème !"+t.message )
            }

        })
    }
}