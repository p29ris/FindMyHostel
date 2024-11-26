package com.example.apps.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apps.models.Hostel
import com.google.firebase.firestore.FirebaseFirestore

class HostelViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _hostelList = MutableLiveData<List<Hostel>>()
    val hostelList: LiveData<List<Hostel>> get() = _hostelList

    // Fetch hostels from Firestore
    fun fetchHostels(query: String? = null) {
        val collection = db.collection("hostels")
        val task = if (query.isNullOrEmpty()) {
            collection.get()
        } else {
            collection.whereEqualTo("location", query).get() // Adjust search logic if needed
        }

        task.addOnSuccessListener { documents ->
            val hostels = documents.mapNotNull { it.toObject(Hostel::class.java) }
            _hostelList.value = hostels
        }
    }
}
