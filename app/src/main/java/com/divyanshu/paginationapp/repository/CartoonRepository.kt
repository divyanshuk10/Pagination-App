package com.divyanshu.paginationapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.divyanshu.paginationapp.api.ApiService
import com.divyanshu.paginationapp.model.CartoonData
import javax.inject.Inject

class CartoonRepository @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, CartoonData>() {

    // suspend fun getCartoonsWithPagination() = apiService.getCartoonsWithPagination(1)

    override fun getRefreshKey(state: PagingState<Int, CartoonData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CartoonData> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getCartoonsWithPagination(currentPage)
            val data = response.body()
            val responseListData = data?.results ?: emptyList()

            LoadResult.Page(
                data = responseListData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}