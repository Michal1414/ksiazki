package com.example.books

import android.annotation.SuppressLint
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
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class BookDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_display)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val backButton = findViewById<ImageButton>(R.id.arrowBackBtn)
        backButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }




        val workKey = intent.getStringExtra("WORK_KEY")

        val rateButton = findViewById<Button>(R.id.rateButton)
        rateButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("WORK_KEY", workKey)
            startActivity(intent)
        }

        val cover: ImageView = findViewById<ImageView>(R.id.coverImage)
        val titleText: TextView = findViewById<TextView>(R.id.TitleText)
        val authorText: TextView = findViewById<TextView>(R.id.authorNameText)
        val descText: TextView = findViewById<TextView>(R.id.DescriptionText)


        lifecycleScope.launch {
            val title = BookInformation.getTitleFromWKey(workKey).toString()
            val author = BookInformation.getAuthorNameFromWKey(workKey).toString()
            val desc = BookInformation.getDescriptionFromWKey(workKey).toString()

            titleText.setText(title)
            authorText.setText(author)
            descText.setText(desc)

            Glide.with(cover)
                .load(BookInformation.getCoverUrlFromWKey(workKey))
                .into(cover)
        }
    }
}