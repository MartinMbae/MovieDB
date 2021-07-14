package com.example.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.adapters.MoviePagingAdapter
import com.example.moviedb.network.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {


    lateinit var moviePagingAdapter: MoviePagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            moviePagingAdapter = MoviePagingAdapter()
            adapter = moviePagingAdapter
        }
    }
//
//    private fun getAllMoviews() {
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(Constants.BASE_URL)
//            .build()
//            .create(ApiInterface::class.java)
//        val retrofitData = retrofitBuilder.getMovies()
//
//        retrofitData.enqueue(object : Callback<MovieData?> {
//            override fun onResponse(call: Call<MovieData?>, response: Response<MovieData?>) {
//                val responseBody = response.body()
//                if (responseBody != null) {
//                    recyclerView.adapter = MovieAdapter(responseBody.results, this@MainActivity)
//                }
//            }
//            override fun onFailure(call: Call<MovieData?>, t: Throwable) {
//            }
//        })
//    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                moviePagingAdapter.submitData(it)
            }
        }
    }
}