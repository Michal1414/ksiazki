package com.example.books

object ReviewRepository {
    val reviews = mutableListOf(
        Review(
            workKey = "/works/OL3140822W",
            reviewText = "Nunc posuere euismod mauris sit amet mollis. Phasellus molestie libero sed massa faucibus, non dapibus justo posuere. Aenean rhoncus odio.",
            rating = 5
        ),
        Review(
            workKey = "/works/OL27729983W",
            reviewText = "Proin sed accumsan mi, eget commodo augue. Aenean neque ex, auctor eu condimentum sit amet, auctor ac libero. Etiam aliquet elit a tellus fringilla, consequat fringilla mauris ultrices. Curabitur luctus tristique metus in faucibus. Mauris a orci sed erat scelerisque pharetra.",
            rating = 3
        ),
        Review(
            workKey = "/works/OL20150260W",
            reviewText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at faucibus augue, dapibus varius augue. Aenean sit amet sodales enim. Nulla facilisi. Aenean ullamcorper vestibulum iaculis. Integer quam lacus, ultrices sed egestas vel, feugiat et enim. Vivamus non mi vehicula.",
            rating = 2
        ),
        Review(
            workKey = "/works/OL261794W",
            reviewText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at faucibus augue, dapibus varius augue. Aenean sit amet sodales enim. Nulla facilisi. Aenean ullamcorper vestibulum iaculis. Integer quam lacus, ultrices sed egestas vel, feugiat et enim. Vivamus non mi vehicula.",
            rating = 2
        )
    )

    fun addReview(review: Review) {
        reviews.add(review)
    }
}