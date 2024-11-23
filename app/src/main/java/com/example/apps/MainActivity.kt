package com.example.apps

import android.os.Bundle
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
import com.example.apps.ui.theme.AppsTheme
import com.google.firebase.auth.FirebaseAuth

// Declare FirebaseAuth instance
private lateinit var auth: FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppsTheme {
                NavigationView()
            }
        }
    }
}

@Composable
fun NavigationView(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome"){WelcomeScreen(navController)}
        composable("login"){LoginScreen(navController)}
        composable("signup"){ SignUpScreen(navController)}
        composable("home"){ HomeScreen(navController)}
        composable("forgot_password") { ForgotPasswordScreen(navController) }

    }
}