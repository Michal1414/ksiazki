package com.example.books

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {

    // liczba gwiazdek
    private lateinit var star1: ImageButton
    private lateinit var star2: ImageButton
    private lateinit var star3: ImageButton
    private lateinit var star4: ImageButton
    private lateinit var star5: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // get and set all book information
        val imageBook: ImageView = findViewById(R.id.bookCoverImg)
        val title: TextView = findViewById(R.id.titleText)
        val description: TextView = findViewById(R.id.myReviewEditText)
        var rating: Int = 0;

        val bundle = intent.extras
        if (bundle != null) {
            imageBook.setImageResource(bundle.getInt("bookCover"))
            title.text = bundle.getString("title")
            description.text = bundle.getString("description")
            rating = bundle.getInt("rating", 0)
        }

        // go back to mainActivity
        val backBtn = findViewById<ImageButton>(R.id.arrowBackBtn)

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // initialize stars AFTER setContentView
        star1 = findViewById(R.id.starButton1)
        star2 = findViewById(R.id.starButton2)
        star3 = findViewById(R.id.starButton3)
        star4 = findViewById(R.id.starButton4)
        star5 = findViewById(R.id.starButton5)



        setStars(rating)

        star1.setOnClickListener { setStars(1) }
        star2.setOnClickListener { setStars(2) }
        star3.setOnClickListener { setStars(3) }
        star4.setOnClickListener { setStars(4) }
        star5.setOnClickListener { setStars(5) }
    }


    private fun setStars(amount: Int) {
        when(amount) {
            0 -> {
                star1.setImageResource(R.drawable.btn_star_big_off)
                star2.setImageResource(R.drawable.btn_star_big_off)
                star3.setImageResource(R.drawable.btn_star_big_off)
                star4.setImageResource(R.drawable.btn_star_big_off)
                star5.setImageResource(R.drawable.btn_star_big_off)
            }

            1 -> {
                star1.setImageResource(R.drawable.btn_star_big_on)
                star2.setImageResource(R.drawable.btn_star_big_off)
                star3.setImageResource(R.drawable.btn_star_big_off)
                star4.setImageResource(R.drawable.btn_star_big_off)
                star5.setImageResource(R.drawable.btn_star_big_off)
            }

            2 -> {
                star1.setImageResource(R.drawable.btn_star_big_on)
                star2.setImageResource(R.drawable.btn_star_big_on)
                star3.setImageResource(R.drawable.btn_star_big_off)
                star4.setImageResource(R.drawable.btn_star_big_off)
                star5.setImageResource(R.drawable.btn_star_big_off)
            }

            3 -> {
                star1.setImageResource(R.drawable.btn_star_big_on)
                star2.setImageResource(R.drawable.btn_star_big_on)
                star3.setImageResource(R.drawable.btn_star_big_on)
                star4.setImageResource(R.drawable.btn_star_big_off)
                star5.setImageResource(R.drawable.btn_star_big_off)
            }

            4 -> {
                star1.setImageResource(R.drawable.btn_star_big_on)
                star2.setImageResource(R.drawable.btn_star_big_on)
                star3.setImageResource(R.drawable.btn_star_big_on)
                star4.setImageResource(R.drawable.btn_star_big_on)
                star5.setImageResource(R.drawable.btn_star_big_off)
            }

            5 -> {
                star1.setImageResource(R.drawable.btn_star_big_on)
                star2.setImageResource(R.drawable.btn_star_big_on)
                star3.setImageResource(R.drawable.btn_star_big_on)
                star4.setImageResource(R.drawable.btn_star_big_on)
                star5.setImageResource(R.drawable.btn_star_big_on)
            }

            else -> {
                star1.setImageResource(R.drawable.btn_star_big_off)
                star2.setImageResource(R.drawable.btn_star_big_off)
                star3.setImageResource(R.drawable.btn_star_big_off)
                star4.setImageResource(R.drawable.btn_star_big_off)
                star5.setImageResource(R.drawable.btn_star_big_off)

            }
        }
    }
}