package com.ironclad.retrofitdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ironclad.retrofitdemo.R
import com.ironclad.retrofitdemo.adapters.UserAdapter
import com.ironclad.retrofitdemo.modelClass.User
import com.ironclad.retrofitdemo.networking.RetrofitClient
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        GlobalScope.launch(Dispatchers.Main) {
            val users = getUsers()
            Log.d("PUI", "$users")
            rvUsers.layoutManager =
                LinearLayoutManager(this@UserActivity, RecyclerView.HORIZONTAL, false)
            rvUsers.adapter = UserAdapter(users, this@UserActivity)
        }
    }

    private suspend fun getUsers(): List<User> {
        val userAPI = RetrofitClient.apiCall
        val responseUser = userAPI.getUsers()
        return if (responseUser.isSuccessful) {
            responseUser.body()!!
        } else {
            emptyList()
        }
    }
}