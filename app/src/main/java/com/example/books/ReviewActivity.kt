package com.example.books

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class ReviewActivity : AppCompatActivity() {

    // Gwiazdki
    private lateinit var star1: ImageButton
    private lateinit var star2: ImageButton
    private lateinit var star3: ImageButton
    private lateinit var star4: ImageButton
    private lateinit var star5: ImageButton

    private var currentRating: Int = 0

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cover: ImageView = findViewById(R.id.bookCoverImg)
        val titleText: TextView = findViewById(R.id.titleText)
        val authorText: TextView = findViewById(R.id.authorText)
        val reviewEditText: EditText = findViewById(R.id.myReviewEditText)
        val saveButton: Button = findViewById(R.id.saveBtn)

        star1 = findViewById(R.id.starButton1)
        star2 = findViewById(R.id.starButton2)
        star3 = findViewById(R.id.starButton3)
        star4 = findViewById(R.id.starButton4)
        star5 = findViewById(R.id.starButton5)

        val workKey = intent.getStringExtra("WORK_KEY") ?: return

        val review = findReviewByWorkKey(workKey)
        currentRating = review?.rating ?: 0
        reviewEditText.setText(review?.reviewText ?: "")

        setStars(currentRating)

        star1.setOnClickListener { setStars(1) }
        star2.setOnClickListener { setStars(2) }
        star3.setOnClickListener { setStars(3) }
        star4.setOnClickListener { setStars(4) }
        star5.setOnClickListener { setStars(5) }

        lifecycleScope.launch {
            val title = BookInformation.getTitleFromWKey(workKey) ?: "Brak tytułu"
            val author = BookInformation.getAuthorNameFromWKey(workKey) ?: "Brak autora"
            val coverUrl = BookInformation.getCoverUrlFromWKey(workKey) ?: ""

            titleText.text = title
            authorText.text = author

            Glide.with(cover)
                .load(coverUrl)
                .into(cover)
        }

        saveButton.setOnClickListener {
            val newText = reviewEditText.text.toString()

            if (review != null) {
                review.reviewText = newText
                review.rating = currentRating
            } else {
                ReviewRepository.reviews.add(
                    Review(
                        workKey = workKey,
                        rating = currentRating,
                        reviewText = newText
                    )
                )
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setStars(amount: Int) {
        currentRating = amount
        val stars = listOf(star1, star2, star3, star4, star5)
        for (i in stars.indices) {
            if (i < amount) stars[i].setImageResource(R.drawable.btn_star_big_on)
            else stars[i].setImageResource(R.drawable.btn_star_big_off)
        }
    }

    private fun findReviewByWorkKey(workKey: String): Review? {
        return ReviewRepository.reviews.find { it.workKey == workKey }
    }
}