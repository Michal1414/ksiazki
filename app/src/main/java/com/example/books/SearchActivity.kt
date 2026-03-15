package com.example.books

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//
//        friendsButton.setOnClickListener {
//            val intent = Intent(this, FriendsActivity::class.java)
//            startActivity(intent)
//        }


        val myBooksBtn: Button = findViewById<Button>(R.id.myBooksButton)

        myBooksBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val searchButton: Button = findViewById(R.id.buttonBookSearch)
        val searchEditText: EditText = findViewById(R.id.searchEditText)
        val hintText: TextView = findViewById<TextView>(R.id.hintText)

        searchButton.setOnClickListener {
            val title = searchEditText.text.toString()

            lifecycleScope.launch {
                val result = BookInformation.getWorkKeyFromTitle(title)

                if (result != null) {
                    val workKey = result
                    val intent = Intent(this@SearchActivity, BookDisplayActivity::class.java)
                    intent.putExtra("WORK_KEY", workKey)

                    startActivity(intent)
                } else {
                    hintText.setText("Niepoprawny tytuł książki")
                }
            }
        }


    }
}