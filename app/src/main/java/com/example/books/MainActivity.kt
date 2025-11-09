package com.example.books

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupReview()
        val friendsButton: Button = findViewById<Button>(R.id.friendsButton)

        friendsButton.setOnClickListener {
            val intent = Intent(this, FriendsActivity::class.java)
            startActivity(intent)
        }
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
                        bookCover = android.R.drawable.ic_menu_gallery,
                        rating = Random.nextInt(0, 6)
                    )
                )
            }
        }

        recyclerView.adapter = ReviewAdapter(reviewList)
    }
}
