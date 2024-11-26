package com.example.apps.models

data class Booking(
    val bookingId: String = "",              // Unique ID for the booking
    val userId: String = "",                 // ID of the user who made the booking
    val hostelId: String = "",               // ID of the booked hostel
    val startDate: com.google.firebase.Timestamp? = null, // Start date of booking
    val endDate: com.google.firebase.Timestamp? = null,   // End date of booking
    val status: String = "",                 // Booking status (e.g., "pending", "confirmed", "cancelled")
    val createdAt: com.google.firebase.Timestamp? = null  // Timestamp of booking creation
)
