package com.example.apps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apps.viewmodels.HostelViewModel

class HostelListActivity : AppCompatActivity() {

    private lateinit var viewModel: HostelViewModel
    private lateinit var adapter: HostelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hostel_list)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewHostels)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HostelAdapter(emptyList())
        recyclerView.adapter = adapter

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(HostelViewModel::class.java)

        // Observe data from ViewModel
        viewModel.hostelList.observe(this, { hostels ->
            adapter.updateList(hostels)
        })

        // Search functionality
        val searchView: androidx.appcompat.widget.SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.fetchHostels(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.fetchHostels(it) }
                return true
            }
        })

        // Initial fetch
        viewModel.fetchHostels()
    }
}