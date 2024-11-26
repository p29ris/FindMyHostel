package com.example.apps

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.apps.ui.theme.AppsTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.FirebaseApp
import com.example.apps.models.Hostel

// Declare FirebaseAuth instance
private lateinit var auth: FirebaseAuth
private lateinit var firestore: FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            AppsTheme {
                NavigationView()
            }
        }
        testFirestoreConnection()
    }
    private fun testFirestoreConnection() {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("test").get()
            .addOnSuccessListener { querySnapshot ->
                Log.d("FirestoreTest", "Success: ${querySnapshot.size()} documents retrieved.")
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreTest", "Error: ${exception.message}")
            }
    }

}

@Composable
fun NavigationView() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("hostel_list") { HostelListScreen(navController) }
        // Define route for hostel detail screen, passing hostelId as an argument
        composable("hostelDetail/{hostelId}") { backStackEntry ->
            val hostelId = backStackEntry.arguments?.getString("hostelId") ?: ""
            HostelDetailScreen(hostelId = hostelId, navController = navController)
        }
    }
}


