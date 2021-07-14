package com.example.moviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.models.Result
import com.example.moviedb.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_holder.view.*

class MovieAdapter(val movieList: List<Result>, val context: Context) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
      return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item_holder, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.tv_movie_name.text = movieList.get(position).title
        holder.tv_movie_desc.text = movieList.get(position).overview
        holder.tv_movie_release_date.text = movieList.get(position).release_date
        holder.tv_movie_rating_text.text = movieList.get(position).vote_average.toString()
        holder.tv_movie_rating_bar.rating = movieList.get(position).vote_average.toFloat()

        Picasso.get()
            .load(Constants.MOVIE_IMAGE_PATH+movieList.get(position).poster_path)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(holder.tv_movie_poster_image)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}


class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val tv_movie_name = view.movieName
    val tv_movie_desc = view.movieDesc
    val tv_movie_release_date = view.movieReleaseDate
    val tv_movie_rating_text = view.ratingText
    val tv_movie_rating_bar= view.ratingBar
    val tv_movie_poster_image= view.posterImageView
}