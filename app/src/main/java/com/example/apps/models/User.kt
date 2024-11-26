package com.example.apps.models

data class User (
    val userId: String = "",                // Unique ID for the user
    val name: String = "",                  // Name of the user
    val email: String = "",                 // Email of the user
    val role: String = "",                  // Role (e.g., "student" or "hostel_owner")
    val profilePicture: String = "",        // URL of the user's profile picture
    val phoneNumber: String = "",           // User's phone number
    val createdAt: com.google.firebase.Timestamp? = null // Timestamp of account creation
)