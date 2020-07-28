package com.ironclad.retrofitdemo.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ironclad.retrofitdemo.R
import com.ironclad.retrofitdemo.modelClass.Album
import com.ironclad.retrofitdemo.modelClass.Post
import com.ironclad.retrofitdemo.networking.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumFragment : Fragment() {

    private var albums: List<Album> = emptyList()
    private val TAG = "AlbumFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_album, container, false)
        val sharedPreferences =
            requireContext().getSharedPreferences("UserID", Context.MODE_PRIVATE)
        val userId = sharedPreferences?.getInt("userID", 0)
        GlobalScope.launch {
            albums = getAlbumByUser(userId!!)
            Log.d(TAG, "$albums")
        }

        return rootView
    }

    private suspend fun getAlbumByUser(userId: Int): List<Album> {
        val albumAPI = RetrofitClient.apiCall
        val responseAlbum = albumAPI.getAlbumsByUser(userId)
        return if (responseAlbum.isSuccessful) {
            responseAlbum.body()!!
        } else {
            emptyList()
        }
    }

}