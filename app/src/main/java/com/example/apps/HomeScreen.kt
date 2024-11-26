package com.example.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.navArgument
import com.example.apps.components.CButton

// Data class for Hostels
data class Hostel(
    val name: String,
    val location: String,
    val price: String,
    val imageRes: Int
)

// Main App
@Composable
fun MainApp() {
    val navController = rememberNavController()
    MaterialTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                Modifier.padding(innerPadding)
            ) {
                composable("home") { HomeScreen(navController) }
                composable("favorites") { FavoritesScreen() }
                composable(
                    "hostel_details/{hostelName}",
                    arguments = listOf(navArgument("hostelName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val hostelName = backStackEntry.arguments?.getString("hostelName") ?: ""
                    HostelDetailsScreen(hostelName = hostelName, navController = navController)
                }
            }
        }
    }
}

// Bottom Navigation Bar
// Updated Bottom Navigation Bar using Material3 NavigationBar and NavigationBarItem
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf("home" to "Home", "favorites" to "Favorites")
    NavigationBar {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination

        items.forEach { (route, label) ->
            NavigationBarItem(
                selected = currentDestination?.route == route,
                onClick = {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (route == "home") R.drawable.ic_home else R.drawable.ic_favorites
                        ),
                        contentDescription = label,
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = { Text(label) }
            )
        }
    }
}


// Home Screen (Provided in Question)
@Composable
fun HomeScreen(navController: NavController) {
    val hostels = remember {
        listOf(
            Hostel("Sunset Hostel", "Nairobi, Kenya", "$150/month", R.drawable.hostel1),
            Hostel("Ocean View", "Mombasa, Kenya", "$200/month", R.drawable.hostel2),
            Hostel("Mountain Lodge", "Eldoret, Kenya", "$180/month", R.drawable.hostel3),
            Hostel("City Stay", "Kisumu, Kenya", "$160/month", R.drawable.hostel4),
            Hostel("Urban Haven", "Nairobi, Kenya", "$175/month", R.drawable.hostel5)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Available Listings",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(hostels) { hostel ->
                HostelItem(hostel) {
                    navController.navigate("hostel_details/${hostel.name}")
                }
            }
        }
    }
}

// Hostel Item
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
                    color = Color.White
                )
            )
            Text(
                text = "Location: ${hostel.location}",
                style = TextStyle(fontSize = 12.sp, color = Color.Gray)
            )
            Text(
                text = "Price: ${hostel.price}",
                style = TextStyle(fontSize = 12.sp, color = Color.Gray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            CButton(
                text = "View",
                onClick = onViewClick
            )
        }
    }
}


// Favorites Screen (Placeholder)
@Composable
fun FavoritesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Favorites Screen", style = TextStyle(fontSize = 24.sp))
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MainApp()
}
