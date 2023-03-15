package com.example.completenavigation.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.completenavigation.R


class PostDetailFragment : Fragment() {

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
        arguments?.let {
            post = it.getParcelable(EXTRA_POST)!!
            postIndex= it.getInt(EXTRA_POST_INDEX, -1)!!
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
        val view = inflater.inflate(R.layout.fragment_post_detail, container, false)

        titleTextView = view.findViewById(R.id.title_post_detail)
        bodyTextView = view.findViewById(R.id.body_post_detail)

        titleTextView.text = post.title
        bodyTextView.text = post.body

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

}