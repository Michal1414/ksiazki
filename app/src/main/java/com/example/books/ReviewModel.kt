package com.example.books

data class ReviewModel(
    val workKey: String,
    val userName: String,
    val title: String,
    var description: String,
    val bookCover: String?,
    var rating: Int
)
