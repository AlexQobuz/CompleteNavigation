package com.example.completenavigation.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.completenavigation.R


class UserDetailFragment : Fragment() {

    companion object {
        const val EXTRA_USER = "user"
        const val EXTRA_USER_INDEX = "userIndex"
    }

    lateinit var user: User
    var userIndex: Int = -1

    lateinit var nameTextView: TextView
    lateinit var usernameTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(EXTRA_USER)!!
            userIndex = it.getInt(EXTRA_USER_INDEX, -1)!!
        }
    }

    /**
     * Ici je récupère les références aux
     * vues à partir de la vue inflatée plutôt que de l'activitée
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_detail, container, false)

        nameTextView = view.findViewById(R.id.name_user)
        usernameTextView = view.findViewById(R.id.username_user)

        nameTextView.text = user.name
        usernameTextView.text = user.username

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_user_detail)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}

