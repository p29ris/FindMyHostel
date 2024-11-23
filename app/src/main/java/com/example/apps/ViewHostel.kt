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
import androidx.navigation.compose.rememberNavController
import com.example.apps.components.CButton
import com.example.apps.ui.theme.AlegreyaFontFamily
import androidx.compose.ui.tooling.preview.Preview

data class Hostel(
    val name: String,
    val location: String,
    val price: String,
    val imageRes: Int
)

@Composable
fun HomeScreen(navController: NavController) {
    val hostels = remember {
        listOf(
            Hostel("Sunset Hostel", "Nairobi, Kenya", "$150/month", R.drawable.hostel1),
            Hostel("Ocean View", "Mombasa, Kenya", "$200/month", R.drawable.hostel2),
            Hostel("Mountain Lodge", "Eldoret, Kenya", "$180/month", R.drawable.hostel3),
            Hostel("City Stay", "Kisumu, Kenya", "$160/month", R.drawable.hostel4),
            Hostel("Urban Haven", "Nairobi, Kenya", "$175/month", R.drawable.hostel5),
            Hostel("Peaceful Pines", "Nakuru, Kenya", "$140/month", R.drawable.hostel6),
            Hostel("Coastal Breeze", "Malindi, Kenya", "$220/month", R.drawable.hostel7),
            Hostel("Hilltop Retreat", "Kericho, Kenya", "$190/month", R.drawable.hostel8),
            Hostel("Lakeview Lodge", "Naivasha, Kenya", "$170/month", R.drawable.hostel9),
            Hostel("Safari Stay", "Narok, Kenya", "$200/month", R.drawable.hostel10),
            Hostel("Skyline Hostel", "Thika, Kenya", "$160/month", R.drawable.hostel11),
            Hostel("Valley View Hostel", "Kitale, Kenya", "$155/month", R.drawable.hostel12)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Page Title
        Text(
            text = "Available Listings",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary // Match button color
            ),
            modifier = Modifier.padding(16.dp)
        )

        // LazyVerticalGrid to display the listings in two columns and make them scrollable
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(hostels) { hostel ->
                HostelItem(hostel, onViewClick = {
                    navController.navigate("hostel_details/${hostel.name}")
                })
            }
        }
    }
}

@Composable
fun HostelItem(hostel: Hostel, onViewClick: () -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = hostel.imageRes),
                contentDescription = "Image of ${hostel.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = hostel.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = "Location: ${hostel.location}",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
            Text(
                text = "Price: ${hostel.price}",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            CButton(
                text = "View",
                onClick = onViewClick
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}