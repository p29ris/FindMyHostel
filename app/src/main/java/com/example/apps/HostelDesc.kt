package com.example.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

// Data class for hostel details
data class HostelDesc(
    val name: String,
    val location: String,
    val price: String,
    val imageRes: Int
)

@Composable
fun HostelDesc(navController: NavController, hostelName: String?) {
    // List of hostels
    val hostels = remember {
        listOf(
            HostelDesc("Sunset Hostel", "Nairobi, Kenya", "$150/month", R.drawable.hostel1),
            HostelDesc("Ocean View", "Mombasa, Kenya", "$200/month", R.drawable.hostel2),
            HostelDesc("Mountain Lodge", "Eldoret, Kenya", "$180/month", R.drawable.hostel3),
            HostelDesc("Green Pastures", "Kisumu, Kenya", "$130/month", R.drawable.hostel4),
            HostelDesc("Seaside Retreat", "Lamu, Kenya", "$170/month", R.drawable.hostel5),
            HostelDesc("City Heights", "Nairobi, Kenya", "$210/month", R.drawable.hostel6),
            HostelDesc("Royal Hostel", "Kisii, Kenya", "$160/month", R.drawable.hostel7),
            HostelDesc("Downtown Hostel", "Nakuru, Kenya", "$140/month", R.drawable.hostel8),
            HostelDesc("Skyline Hostel", "Nairobi, Kenya", "$190/month", R.drawable.hostel9),
            HostelDesc("Lakeview Hostel", "Naivasha, Kenya", "$150/month", R.drawable.hostel10),
            HostelDesc("Sunrise Hostel", "Mombasa, Kenya", "$180/month", R.drawable.hostel11),
            HostelDesc("Mountain View", "Eldoret, Kenya", "$200/month", R.drawable.hostel12)
        )
    }


    // Find the selected hostel
    val hostel = hostels.firstOrNull { it.name == hostelName }

    // Display hostel details if found, else show error message
    if (hostel != null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            Text(
                text = hostel.name,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(16.dp)
            )

            Image(
                painter = painterResource(id = hostel.imageRes),
                contentDescription = "Image of ${hostel.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Location: ${hostel.location}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "Price: ${hostel.price}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Go Back")
            }
        }
    } else {
        Text("Hostel not found")
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun HostelDescPreview() {
    val navController = rememberNavController()
    // Provide a sample hostel name for preview
    HostelDesc(navController, "Sunset Hostel")
}
