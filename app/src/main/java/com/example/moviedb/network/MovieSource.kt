package com.example.moviedb.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviedb.network.interfaces.ApiInterface
import com.example.moviedb.models.Result

class MovieSource(val apiService: ApiInterface) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try{
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getMovies(nextPage)
            val nextPageNumber : Int = response.page
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }



    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }
}