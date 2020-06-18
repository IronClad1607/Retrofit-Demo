package com.ironclad.retrofitdemo.networking

import com.ironclad.retrofitdemo.modelClass.User
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/users")
    suspend fun getUsers():Response<List<User>>
}