package com.example.books

import FriendsAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_freinds)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val friendsList = listOf(
            Friend("Alice"),
            Friend("Bob"),
            Friend("Charlie"),
            Friend("David"),
            Friend("Eve"),
            Friend("Frank"),
            Friend("Grace"),
            Friend("Hannah"),
            Friend("Ian"),
            Friend("Jack")
        )

        val recyclerView: RecyclerView = findViewById(R.id.myFriendsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FriendsAdapter(friendsList) { friend ->
            val intent = Intent(this, FriendsReviewActivity::class.java)
            intent.putExtra("friendName", friend.name)
            startActivity(intent)
        }



        val myBooksBtn: Button = findViewById<Button>(R.id.myBooksButton)

        myBooksBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}