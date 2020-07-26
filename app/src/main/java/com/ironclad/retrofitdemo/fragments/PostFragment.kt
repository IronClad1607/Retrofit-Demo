package com.ironclad.retrofitdemo.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironclad.retrofitdemo.R
import com.ironclad.retrofitdemo.adapters.PostAdapter
import com.ironclad.retrofitdemo.modelClass.Post
import com.ironclad.retrofitdemo.networking.RetrofitClient
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostFragment : Fragment() {
    private var posts: List<Post> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_post, container, false)
        val sharedPreferences =
            requireContext().getSharedPreferences("UserID", Context.MODE_PRIVATE)
        val userId = sharedPreferences?.getInt("userID", 0)
        val llManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch(Dispatchers.Main) {
            posts = getPostByUser(userId!!)
            Log.d("PUI", "$posts")
            rootView.rvPost.layoutManager = llManager
            rootView.rvPost.adapter = PostAdapter(posts)
        }
        return rootView
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