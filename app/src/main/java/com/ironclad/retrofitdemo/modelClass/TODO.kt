package com.ironclad.retrofitdemo.modelClass

data class TODO(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)