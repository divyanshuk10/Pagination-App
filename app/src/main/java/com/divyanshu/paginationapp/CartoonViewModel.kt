package com.divyanshu.paginationapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.divyanshu.paginationapp.repository.CartoonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartoonViewModel @Inject constructor(private val repository: CartoonRepository) :
    ViewModel() {

    private val TAG = CartoonViewModel::class.java.canonicalName
    val cartoonListData = Pager(PagingConfig(pageSize = 1)) {
        repository
    }.flow.cachedIn(viewModelScope)
}