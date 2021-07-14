package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.models.Result
import com.example.moviedb.utils.Constants
import com.squareup.picasso.Picasso

class MoviePagingAdapter: PagingDataAdapter<Result, MoviePagingAdapter.MyViewHolder>(DifficultCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item_holder, parent, false))

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(getItem(position)!!)

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val tv_movie_name: TextView = view.findViewById<TextView>(R.id.movieName)
        val tv_movie_desc: TextView = view.findViewById<TextView>(R.id.movieDesc)
        val tv_movie_release_date: TextView = view.findViewById<TextView>(R.id.movieReleaseDate)
        val tv_movie_rating_text: TextView = view.findViewById<TextView>(R.id.ratingText)
        val tv_movie_rating_bar: RatingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val tv_movie_poster_image: ImageView = view.findViewById<ImageView>(R.id.posterImageView)

        fun bind(movieInfo: Result){

            tv_movie_name.text = movieInfo.title
            tv_movie_desc.text = movieInfo.overview
            tv_movie_release_date.text = movieInfo.release_date
            tv_movie_rating_text.text = movieInfo.vote_average.toString()
            tv_movie_rating_bar.rating = movieInfo.vote_average.toFloat()

            Picasso.get()
                .load(Constants.MOVIE_IMAGE_PATH+movieInfo.poster_path)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(tv_movie_poster_image)
        }
    }

    class DifficultCallBack: DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title == newItem.title &&  oldItem.overview == newItem.overview
        }

    }
}