package com.example.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendsReviewAdapter(private val reviewList: List<ReviewModel>) :
    RecyclerView.Adapter<FriendsReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBook: ImageView = itemView.findViewById(R.id.imageView2)
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionRecycleView)
        val userName: TextView = itemView.findViewById(R.id.userNameTextView)
        val date: TextView = itemView.findViewById(R.id.dateTextView)
        val stars = listOf<ImageView>(
            itemView.findViewById(R.id.star1Img),
            itemView.findViewById(R.id.star2Img),
            itemView.findViewById(R.id.star3Img),
            itemView.findViewById(R.id.star4Img),
            itemView.findViewById(R.id.star5Img)
        )
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

        // show rating stars
        holder.stars.forEachIndexed { index, imageView ->
            val starRes = if (index < item.rating)
                android.R.drawable.btn_star_big_on
            else
                android.R.drawable.btn_star_big_off
            imageView.setImageResource(starRes)
        }
    }

    override fun getItemCount(): Int = reviewList.size
}
