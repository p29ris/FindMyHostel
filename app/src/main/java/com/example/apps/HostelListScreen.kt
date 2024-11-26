@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.apps

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apps.models.Hostel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HostelListScreen(navController: NavController) {
    var hostels by remember { mutableStateOf<List<Hostel>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch hostels from Firestore
    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance()
            .collection("hostels")
            .get()
            .addOnSuccessListener { snapshot ->
                val fetchedHostels = snapshot.documents.map { doc ->
                    Hostel(
                        hostelId = doc.id,
                        name = doc.getString("name") ?: "",
                        price = doc.getDouble("price") ?: 0.0,
                        description = doc.getString("description") ?: "",
                        images = doc.getString("imageUrl") ?: ""
                    )
                }
                hostels = fetchedHostels
                isLoading = false
            }
            .addOnFailureListener { exception ->
                Toast.makeText(navController.context, "Failed to load hostels: ${exception.message}", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hostel List",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary)
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(hostels) { hostel ->
                            HostelCard(hostel = hostel, navController = navController)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun HostelCard(hostel: Hostel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.hostel1), // Add default image here
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = hostel.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
                Text(
                    text = "Price: ${hostel.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = hostel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2
                )
                Button(
                    onClick = {
                        // Navigate to the Hostel Detail Screen
                        navController.navigate("hostelDetail/${hostel.hostelId}")
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("View")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HostelListScreenPreview() {
    HostelListScreen(navController = rememberNavController())
}
