package com.example.books

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupReview()

        val reviews = ReviewRepository.reviews
        for (review in reviews) {
            println(review.reviewText)
            println(review.rating)
        }

//        friendsButton.setOnClickListener {
//            val intent = Intent(this, FriendsActivity::class.java)
//            startActivity(intent)
//        }


        val searchButton: Button = findViewById<Button>(R.id.searchButton)

        searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }


//
//        lifecycleScope.launch {
//            val workKey = BookInformation.getWorkKeyFromTitle("the%20bad")
//            println("Work key: $workKey")
//            BookInformation.getDescriptionFromWKey(workKey)
//            BookInformation.getAuthorNameFromWKey(workKey)
//        }

    }

    private fun setupReview() {

        val recyclerView = findViewById<RecyclerView>(R.id.myBooksList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val reviewList = ArrayList<ReviewModel>()
        val reviews = ReviewRepository.reviews

        lifecycleScope.launch {

            for (review in reviews) {

                val title = BookInformation.getTitleFromWKey(review.workKey).toString()
                val bookCover = BookInformation.getCoverUrlFromWKey(review.workKey)

                reviewList.add(
                    ReviewModel(
                        workKey = review.workKey,
                        userName = "Twoja ocena",
                        title = title,
                        description = review.reviewText,
                        bookCover = bookCover,
                        rating = review.rating
                    )
                )
            }

            recyclerView.adapter = ReviewAdapter(reviewList)
        }
    }
}
