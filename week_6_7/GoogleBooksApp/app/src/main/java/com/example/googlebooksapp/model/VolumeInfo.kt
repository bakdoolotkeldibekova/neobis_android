package com.example.googlebooksapp.model

data class VolumeInfo(
    var title: String,
    var authors: List<String>,
    var averageRating: Float,
    var infoLink: String,
    var pageCount: Int,
    var publishedDate: String,
    var categories: List<String>,
    var imageLinks: ImageLinks
)
