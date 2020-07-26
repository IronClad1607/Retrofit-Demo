package com.ironclad.retrofitdemo.networking

import com.ironclad.retrofitdemo.modelClass.Post
import com.ironclad.retrofitdemo.modelClass.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("/posts")
    suspend fun getPostByUser(@Query("userId") userId: Int): Response<List<Post>>
}