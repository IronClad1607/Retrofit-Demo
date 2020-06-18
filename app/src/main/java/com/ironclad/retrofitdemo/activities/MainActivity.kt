package com.ironclad.retrofitdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ironclad.retrofitdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cvUsers.setOnClickListener {
            val userIntent = Intent(this, UserActivity::class.java)
            startActivity(userIntent)
        }

        cvPosts.setOnClickListener {
            val postIntent = Intent(this, PostActivity::class.java)
            startActivity(postIntent)
        }

        cvComments.setOnClickListener {
            val commentIntent = Intent(this, CommentActivity::class.java)
            startActivity(commentIntent)
        }

        cvTodo.setOnClickListener {
            val todoIntent = Intent(this, TODOActivity::class.java)
            startActivity(todoIntent)
        }
    }
}