package com.example.completenavigation.post

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
import com.example.completenavigation.post.PostDetailFragment.Companion.EXTRA_POST
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class PostsFragment : Fragment() {

    lateinit var myPostAdapter: PostAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerViewUsers: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewUsers = view.findViewById(R.id.recyclerview_posts)
        recyclerViewUsers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerViewUsers.layoutManager = linearLayoutManager

        // il faut que je passe en paramètre
        // la fonction lambda que j'ai créé
        // dans PostAdapter
        myPostAdapter = PostAdapter(requireContext(), onClick = {post ->
            Log.d("PostFragment", "J'ai cliqué sur un post !")
            // Je récupère le fragment et je le stock dans une variable
            val fragment = PostDetailFragment()
            val args = Bundle()
            args.putParcelable(EXTRA_POST, post)
            fragment.arguments = args
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        })
        recyclerViewUsers.adapter = myPostAdapter
        getPosts()

    }

    /**
     * le fragment est passé à l'état STARTED.
     */
    override fun onStart() {
        super.onStart()
    }

    /**
     * le fragment est passé à l'état
     * RESUMED et est désormais actif
     * (peut répondre à l'entrée utilisateur).
     */
    override fun onResume() {
        super.onResume()
    }

    /**
     * le fragment est revenu à l'état STARTED.
     * L'utilisateur peut voir l'interface utilisateur.
     */
    override fun onPause() {
        super.onPause()
    }

    /**
     * le fragment est revenu à l'état
     * CREATED.
     * L'objet est instancié,
     * mais n'est plus affiché à l'écran.
     */
    override fun onStop() {
        super.onStop()
    }

    /**
     * appelé juste avant que le
     * fragment passe à l'état DESTROYED.
     * La vue a déjà été supprimée
     * de la mémoire,
     * mais l'objet fragment existe toujours.
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }

    /**
     * le fragment passe à l'état DESTROYED.
     */
    override fun onDestroy() {
        super.onDestroy()
    }

    private fun getPosts() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(com.example.completenavigation.user.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostInterface::class.java)

        val retrofitData = retrofitBuilder.getAllPosts()

        retrofitData.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val responseBody = response.body()!!

                myPostAdapter.setItems(responseBody)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("Activity main","Les users ne peuvent pas être afficher suite à un problème !"+t.message )
            }

        })
    }

}