package com.ironclad.retrofitdemo.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ironclad.retrofitdemo.R
import com.ironclad.retrofitdemo.modelClass.Post
import com.ironclad.retrofitdemo.networking.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostFragment : Fragment() {
    private var posts: List<Post> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreferences = this.activity?.getSharedPreferences("UserID", Context.MODE_PRIVATE)
        val userId = sharedPreferences?.getInt("userID", 0)
        GlobalScope.launch {
            posts = getPostByUser(userId!!)
            Log.d("PUI", "$posts")
        }
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    private suspend fun getPostByUser(userId: Int): List<Post> {
        val postAPI = RetrofitClient.apiCall
        val responsePost = postAPI.getPostByUser(userId)
        return if (responsePost.isSuccessful) {
            responsePost.body()!!
        } else {
            emptyList()
        }
    }

}