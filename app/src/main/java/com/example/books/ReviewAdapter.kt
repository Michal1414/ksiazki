package com.example.books

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private val reviewList: List<ReviewModel>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBook: ImageView = itemView.findViewById(R.id.imageView2)
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionRecycleView)
        val userName: TextView = itemView.findViewById(R.id.userNameTextView)
        val date: TextView = itemView.findViewById(R.id.dateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_row, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = reviewList[position]
        holder.imageBook.setImageResource(item.bookCover)
        holder.title.text = item.title
        holder.description.text = item.description
        holder.userName.text = item.userName
        holder.date.text = item.date

        // Add click listener to the whole item
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ReviewActivity::class.java)

            intent.putExtra("bookCover", item.bookCover)
            intent.putExtra("title", item.title)
            intent.putExtra("description", item.description)
            intent.putExtra("userName", item.userName)
            intent.putExtra("date", item.date)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = reviewList.size
}
