package com.example.completenavigation.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.completenavigation.R

class PostDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_POST = "post"
        const val EXTRA_POST_INDEX = "postIndex"
    }

    lateinit var post: Post
    var postIndex: Int = -1

    lateinit var titleTextView: TextView
    lateinit var bodyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        post = intent.getParcelableExtra<Post>(EXTRA_POST)!!
        postIndex = intent.getIntExtra(EXTRA_POST_INDEX, -1)

        titleTextView = findViewById(R.id.title_post_detail)
        bodyTextView = findViewById(R.id.body_post_detail)

        titleTextView.text = post.title
        bodyTextView.text = post.body

        /**
         * Je déclare la toolbar pour pouvoir l'utiliser
         */
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        // défini la toolbar pour l'utiliser
        setSupportActionBar(toolbar)
        // Affiche la toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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