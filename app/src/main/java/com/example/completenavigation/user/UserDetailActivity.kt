package com.example.completenavigation.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import com.example.completenavigation.R

class UserDetailActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_user_detail)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        user = intent.getParcelableExtra<User>(EXTRA_USER)!!
        userIndex = intent.getIntExtra(EXTRA_USER_INDEX, -1)

        nameTextView = findViewById(R.id.name_user)
        usernameTextView = findViewById(R.id.username_user)

        nameTextView.text = user.name
        usernameTextView.text = user.username


    }

    /**
     * méthode pour pouvoir utiliser le bouton back.
     * Cela permet de garder la hiérarchie si les activité
     * sont déclaré dans le manifest
     */
    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return super.onSupportNavigateUp()
    }

}