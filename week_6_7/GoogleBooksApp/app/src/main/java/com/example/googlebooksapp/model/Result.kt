package com.example.googlebooksapp.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("items")
    var bookList: List<Book>
)
