package com.example.books

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupReview()
    }

    private fun setupReview() {
        val recyclerView = findViewById<RecyclerView>(R.id.myBooksList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val reviewList = ArrayList<ReviewModel>().apply {
            repeat(10) { i ->
                add(
                    ReviewModel(
                        userName = "Użytkownik $i",
                        date = "2025-11-${(1..30).random()}",
                        title = "Książka nr $i",
                        description = "To przykładowa recenzja książki numer $i. Bardzo ciekawa pozycja, polecam!",
                        bookCover = android.R.drawable.ic_menu_gallery
                    )
                )
            }
        }

        recyclerView.adapter = ReviewAdapter(reviewList)
    }
}
