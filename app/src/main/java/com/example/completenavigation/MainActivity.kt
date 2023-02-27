package com.example.completenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.completenavigation.home.HomeFragment
import com.example.completenavigation.post.PostsFragment
import com.example.completenavigation.user.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView

    /**
     * Dans la méthode onCreated : les opérations servent à mettre en place
     * l'interface graphique, initialiser les variables,
     * configurer les listeners ou à se connecter au modèle
     *
     * l'activitée est créée mais l'utilisateur ne la
     * voit pas encore et ne peut pas intéragir avec
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.users -> {
                    loadFragment(UserFragment())
                    true
                }
                R.id.posts -> {
                    loadFragment(PostsFragment())
                    true
                }
                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    /**
     * cette méthode est appelée par le système lorsque
     * l'activité entre dans l'état Started
     *
     * L'interface graphique devient visible à l'utilisateur,
     * mais il ne peut pas encore intéragir avecles différents éléments graphiques
     */
    override fun onStart() {
        super.onStart()
    }

    /**
     * Cette méthode est appelée lorsque l'activité entre dans l'état Resumed.
     * L'activité devient entièrement opérationnelle.
     *
     * L'uitilisateur peut utiliser l'application et
     * cliquer sur les différents éléments graphiques
     */
    override fun onResume() {
        super.onResume()
    }

    /**
     * Cette méthode est appelée lorsque l'activité
     * entre dans l'état Paused.
     * Une Activity passe par exemple dans
     * l’état Paused lorsqu’une AlertDialog est affichée
     */
    override fun onPause() {
        super.onPause()
    }

    /**
     * Cette méthode est appelée lorsque l'activité
     * entre dans l'état Stopped. Par exemple,
     * lorsqu'une nouvelle activité est démarrée,
     * l'activité appelante va se retrouver dans cet état
     */
    override fun onStop() {
        super.onStop()
    }

    /**
     * Cette méthode est appelée lorsque
     * l'activité est arrêtée. Par exemple,
     * ce peut être après avoir appelée la
     * méthode finish(), ou si le système
     * décide d'arrêter l'activité pour libérer de la mémoire.
     */
    override fun onDestroy() {
        super.onDestroy()
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, fragment)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack(HomeFragment().toString())
        transaction.commit()
    }

}