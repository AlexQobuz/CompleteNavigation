package com.example.completenavigation.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.completenavigation.R
import com.example.completenavigation.post.PostDetailFragment.Companion.EXTRA_POST

class PostsFragment : Fragment(), OnBackPressedDispatcherOwner {

    lateinit var myPostAdapter: PostAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerViewUsers: RecyclerView
    // Déclarer le dispatcher de retour en arrière
    private lateinit var backPressedDispatcher: OnBackPressedDispatcher
    private val postsViewModel: PostsViewModel by viewModels()
    private lateinit var viewModel: PostsViewModel

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
        return inflater.inflate(R.layout.fragment_posts, container, false)
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

        // Je créer une instance de "UserViewModel" en utilisant "viewModelProvider..."
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        /**
         * Observer les mises à jour de la liste de posts
         * Ici, postListLiveData est une instance de "MutableLiveData" dans "PostsViewModel"
         * qui est une mise à jour de cette variable LiveData.
         */

        viewModel.postListLiveData.observe(viewLifecycleOwner, { postList ->
            myPostAdapter.setItems(postList)
        })

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

    override fun getOnBackPressedDispatcher(): OnBackPressedDispatcher {
        return backPressedDispatcher
    }

}