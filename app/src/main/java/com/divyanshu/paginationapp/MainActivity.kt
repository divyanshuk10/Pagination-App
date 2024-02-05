package com.divyanshu.paginationapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.divyanshu.paginationapp.adapter.CartoonAdapter
import com.divyanshu.paginationapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CartoonAdapter
    private val viewModel: CartoonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
        loadRecyclerViewData()
    }

    private fun loadRecyclerViewData() {
        lifecycleScope.launch {
            viewModel.cartoonListData.collect {
                adapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = CartoonAdapter()
        binding.included.rlvCartoon.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
        }
    }
}