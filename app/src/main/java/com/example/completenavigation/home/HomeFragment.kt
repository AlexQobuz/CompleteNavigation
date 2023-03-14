package com.example.completenavigation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.completenavigation.R

class HomeFragment : Fragment(), OnBackPressedDispatcherOwner {

    lateinit var myHomeAdapter: HomeAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerViewImgHome: RecyclerView
    private lateinit var backPressedDispatcher : OnBackPressedDispatcher
    //private val PhotoViewModel : PhotoViewModel by viewModels()
    private lateinit var viewModel: PhotoViewModel

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
        return inflater.inflate(R.layout.fragment_home, container, false)
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

        recyclerViewImgHome = view.findViewById(R.id.recyclerview_photos_home)
        recyclerViewImgHome.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerViewImgHome.layoutManager = linearLayoutManager

        // Ici je réutilise la fonction
        // lambda pour l'évènement au click
        myHomeAdapter = HomeAdapter(requireContext())

        recyclerViewImgHome.adapter = myHomeAdapter

        // Je créer une instance de "PhotoViewModle" en utilisant "viewModelProvider..."
        viewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)
        /**
         * Observer les mises à jour de la liste de photos
         * Ici, photoListLiveData est une instance de "MutableLiveData" dans "PhotoViewModel"
         * qui est une mise à jour de cette variable LiveData.
         */
        viewModel.photoListLiveData.observe(viewLifecycleOwner, { photoList ->
            myHomeAdapter.setPhotosHomeAdapter(photoList)
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