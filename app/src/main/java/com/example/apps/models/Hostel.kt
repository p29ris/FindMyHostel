package com.example.apps.models

import com.google.firebase.Timestamp

data class Hostel(
    val hostelId: String = "",               // Unique ID for the hostel
    val name: String = "",                   // Name of the hostel
    val location: String = "",               // Location of the hostel
    val description: String = "",            // Description of the hostel
    val ownerId: String = "",                // ID of the owner (links to a User)
    val images: String = "",  // List of image URLs
    val price: Double = 0.0,         // Monthly price for a room
    val availableRooms: Int = 0,             // Number of available rooms
    val totalRooms: Int = 0,                 // Total number of rooms
    val amenities: List<String> = emptyList(), // List of amenities, e.g., WiFi, Water
    val rating: Double = 0.0,
    val createdAt: Timestamp? = null // Timestamp of hostel creation
)
