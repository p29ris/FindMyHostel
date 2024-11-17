package com.example.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apps.components.CButton
import com.example.apps.ui.theme.AlegreyaFontFamily

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Background Image (Optional)
        Image(
            painter = painterResource(id = R.drawable.bg9), // Optional background image
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.logo_hostel), // Optional logo
                contentDescription = null,
                modifier = Modifier
                    .width(300.dp)
                    .height(220.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Home Screen",
                fontSize = 32.sp,
                fontFamily = AlegreyaFontFamily,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Example Buttons
            CButton(
                text = "View Profile",
//                onClick = {
//                    // Navigate to profile screen (you can create a profile screen)
//                    navController.navigate("profile")
//                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CButton(
                text = "Settings",
//                onClick = {
//                    // Navigate to settings screen (you can create a settings screen)
//                    navController.navigate("settings")
//                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Log out button
            CButton(
                text = "Log Out",
                onClick = {
                    // Implement logout functionality (e.g., clearing user session)
                    navController.navigate("welcome") // Navigate back to the welcome screen
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 620)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
