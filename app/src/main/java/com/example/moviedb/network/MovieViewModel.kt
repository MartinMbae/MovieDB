package com.example.moviedb.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviedb.models.Result
import com.example.moviedb.network.interfaces.ApiInterface
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {

    lateinit var retroService:  ApiInterface

    init {
        retroService = RetroInstance.getRetroInstance().create(ApiInterface::class.java)
    }

    fun getListData() : Flow<PagingData<Result>> {
        return Pager (config = PagingConfig(pageSize = 40, maxSize = 200),
            pagingSourceFactory = {MovieSource(retroService)}).flow.cachedIn(viewModelScope)


    }


}