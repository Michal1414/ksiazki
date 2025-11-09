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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageBook: ImageView = findViewById(R.id.bookCoverImg)
        val title: TextView = findViewById(R.id.titleText)
        val description: TextView = findViewById(R.id.myReviewEditText)
        var rating: Int;

        val bundle = intent.extras
        if (bundle != null) {
            imageBook.setImageResource(bundle.getInt("bookCover"))
            title.text = bundle.getString("title")
            description.text = bundle.getString("description")
            rating = bundle.getInt("rating", 0)
        }






        val backBtn = findViewById<ImageButton>(R.id.arrowBackBtn)

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}