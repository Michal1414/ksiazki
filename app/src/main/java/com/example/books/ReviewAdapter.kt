package com.example.books

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ReviewAdapter(private val reviewList: List<ReviewModel>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBook: ImageView = itemView.findViewById(R.id.imageView2)
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionRecycleView)
        val userName: TextView = itemView.findViewById(R.id.userNameTextView)


        val star1: ImageView = itemView.findViewById(R.id.star1Img)
        val star2: ImageView = itemView.findViewById(R.id.star2Img)
        val star3: ImageView = itemView.findViewById(R.id.star3Img)
        val star4: ImageView = itemView.findViewById(R.id.star4Img)
        val star5: ImageView = itemView.findViewById(R.id.star5Img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_row, parent, false)

        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {

        val item = reviewList[position]

        // okładka z internetu
        Glide.with(holder.imageBook.context)
            .load(item.bookCover)
            .into(holder.imageBook)

        holder.title.text = item.title
        holder.description.text = item.description
        holder.userName.text = item.userName

        holder.itemView.setOnClickListener {

            val context = holder.itemView.context
            val intent = Intent(context, ReviewActivity::class.java)

            intent.putExtra("WORK_KEY", item.workKey)
            intent.putExtra("rating", item.rating)

            context.startActivity(intent)
        }

        val stars = listOf(
            holder.star1,
            holder.star2,
            holder.star3,
            holder.star4,
            holder.star5
        )

        for (i in stars.indices) {
            if (i < item.rating) {
                stars[i].setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                stars[i].setImageResource(android.R.drawable.btn_star_big_off)
            }
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}