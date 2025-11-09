package com.example.books

data class ReviewModel(
    val userName: String,
    val date: String,
    val title: String,
    val description: String,
    val bookCover: Int,
    val rating: Int
)
