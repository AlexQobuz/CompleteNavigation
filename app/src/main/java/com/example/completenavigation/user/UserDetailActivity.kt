package com.example.completenavigation.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.completenavigation.R

class UserDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_USER = "user"
        val EXTRA_USER_INDEX = "userIndex"
    }

    lateinit var user: User
    var userIndex: Int = -1

    lateinit var idTextView: TextView
    lateinit var nameTextView: TextView
    lateinit var usernameTextView: TextView
     lateinit var emailTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        user = intent.getParcelableExtra<User>(EXTRA_USER)!!
        userIndex = intent.getIntExtra(EXTRA_USER_INDEX, -1)

    }

}