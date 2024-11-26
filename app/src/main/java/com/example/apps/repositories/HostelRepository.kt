package com.example.apps.repositories

import com.example.apps.models.Hostel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class HostelRepository {
    class HostelRepository {

        private val firestore = FirebaseFirestore.getInstance()
        private val hostelsCollection = firestore.collection("hostels")

        fun addHostel(hostel: Hostel, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
            hostelsCollection.add(hostel)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { exception ->
                    onFailure(exception.message ?: "Failed to add hostel")
                }
        }

        // Fetch all hostels
        fun fetchHostels(
            onSuccess: (List<Hostel>) -> Unit,
            onFailure: (String) -> Unit
        ) {
            hostelsCollection.orderBy("rating", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { querySnapshot ->
                    val hostels =
                        querySnapshot.documents.mapNotNull { it.toObject(Hostel::class.java) }
                    onSuccess(hostels)
                }
                .addOnFailureListener { exception ->
                    onFailure(exception.message ?: "Failed to fetch hostels")
                }
        }

        // Fetch hostels by owner ID (for the hostel owner view)
        fun fetchHostelsByOwner(
            ownerId: String,
            onSuccess: (List<Hostel>) -> Unit,
            onFailure: (String) -> Unit
        ) {
            hostelsCollection.whereEqualTo("ownerID", ownerId).get()
                .addOnSuccessListener { querySnapshot ->
                    val hostels =
                        querySnapshot.documents.mapNotNull { it.toObject(Hostel::class.java) }
                    onSuccess(hostels)
                }
                .addOnFailureListener { exception ->
                    onFailure(exception.message ?: "Failed to fetch hostels by owner")
                }
        }

        // Update an existing hostel's details
        fun updateHostel(
            hostelId: String,
            updatedHostel: Map<String, Any>,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
        ) {
            hostelsCollection.document(hostelId).update(updatedHostel)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { exception ->
                    onFailure(exception.message ?: "Failed to update hostel")
                }
        }

        // Delete a hostel by ID
        fun deleteHostel(
            hostelId: String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
        ) {
            hostelsCollection.document(hostelId).delete()
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { exception ->
                    onFailure(exception.message ?: "Failed to delete hostel")
                }
        }

        fun fetchHostelById(
            hostelId: String,
            onSuccess: (Hostel?) -> Unit,
            onFailure: (String) -> Unit
        ) {
            hostelsCollection.document(hostelId).get()
                .addOnSuccessListener { documentSnapshot ->
                    val hostel = documentSnapshot.toObject(Hostel::class.java)
                    onSuccess(hostel)
                }
                .addOnFailureListener { exception ->
                    onFailure(exception.message ?: "Failed to fetch hostel by ID")
                }
        }


    }

}