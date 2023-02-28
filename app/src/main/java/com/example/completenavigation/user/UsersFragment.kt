package com.example.completenavigation.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.completenavigation.R
import com.example.completenavigation.user.UserDetailFragment.Companion.EXTRA_USER
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class UserFragment : Fragment(), OnBackPressedDispatcherOwner {

    lateinit var myAdapter: UserAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerViewUsers: RecyclerView
    // Déclarer le dispatcher de retour en arrière
    private lateinit var backPressedDispatcher: OnBackPressedDispatcher

    /**
     * le fragment a été instancié et
     * présente l'état CREATED.
     * Cependant, la vue correspondante
     * n'a pas encore été créée.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtenir le dispatcher de retour en arrière de l'activité parente
        backPressedDispatcher = requireActivity().onBackPressedDispatcher

        // Ajouter un rappel à la pile de rappels de retour en arrière
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Gérer l'événement de retour en arrière ici
                // Par exemple, naviguer vers le fragment précédent
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        backPressedDispatcher.addCallback(this, callback)
    }

    /**
     * cette méthode vous permet de
     * gonfler la mise en page.
     * Le fragment est passé à l'état CREATED
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    /**
     * cette méthode est appelée après la
     * création de la vue.
     * Avec cette méthode, vous appelez
     * généralement findViewById()
     * pour lier des vues spécifiques à des propriétés.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewUsers = view.findViewById(R.id.recyclerview_users)
        recyclerViewUsers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerViewUsers.layoutManager = linearLayoutManager

        // Ici je réutilise la fonction
        // lambda pour l'événement au click
        myAdapter = UserAdapter(requireContext(), onClick = { user ->
            Log.d("UserFragment", "Logique du click")
            val intent = Intent(requireContext(), UserDetailFragment::class.java)
            intent.putExtra(EXTRA_USER, user)
            requireActivity().startActivity(intent)
        })
        recyclerViewUsers.adapter = myAdapter
        getUsers()

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

    /**
     * Fonction qui utilise retrofit pour le call API
     */
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

    /**fun onBackPressedDispatcher(): OnBackPressedDispatcher {
        return backPressedDispatcher
    }*/

    override fun getOnBackPressedDispatcher(): OnBackPressedDispatcher {
        return backPressedDispatcher
    }

}