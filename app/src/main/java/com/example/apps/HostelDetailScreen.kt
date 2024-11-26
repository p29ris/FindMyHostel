package com.example.apps

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apps.models.Hostel
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostelDetailScreen(hostelId: String, navController: NavController) {
    var hostel by remember { mutableStateOf<Hostel?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch the hostel data based on the hostelId
    LaunchedEffect(hostelId) {
        FirebaseFirestore.getInstance()
            .collection("hostels")
            .document(hostelId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    hostel = document.toObject(Hostel::class.java)
                    isLoading = false
                } else {
                    Toast.makeText(navController.context, "Hostel not found", Toast.LENGTH_SHORT).show()
                    isLoading = false
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(navController.context, "Failed to load hostel: ${exception.message}", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hostel Details", color = Color.White, fontSize = 20.sp) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        content = { padding ->
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                hostel?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Hostel Image
                        Image(
                            painter = painterResource(id = R.drawable.hostel1), // Add default image if no image in Firestore
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(bottom = 16.dp)
                        )

                        // Hostel Name
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Hostel Location
                        Text(
                            text = "📍 Location: ${it.location}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Hostel Description
                        Text(
                            text = "📝 Description: ${it.description}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Hostel Price
                        Text(
                            text = "💲 Price: Ksh ${it.price}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Available and Total Rooms
                        Row(
                            modifier = Modifier.padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "🏠 Available Rooms: ${it.availableRooms}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "🏘️ Total Rooms: ${it.totalRooms}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                        // Hostel Amenities
                        Text(
                            text = "✅ Amenities:",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        it.amenities.forEach { amenity ->
                            Text(
                                text = "• $amenity",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                            )
                        }

                        // Hostel Rating
                        Text(
                            text = "⭐ Rating: ${it.rating} / 5",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )

                        // Back Button
                        Button(
                            onClick = { navController.popBackStack() },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Back to Hostels List", color = Color.White)
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HostelDetailScreenPreview() {
    HostelDetailScreen(hostelId = "hostel_id_example", navController = rememberNavController())
}
