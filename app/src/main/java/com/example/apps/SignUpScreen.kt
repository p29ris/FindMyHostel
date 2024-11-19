package com.example.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apps.components.CButton
import com.example.apps.components.CTextField
import com.example.apps.components.DontHaveAccountRow
import com.example.apps.ui.theme.AlegreyaFontFamily
import com.example.apps.signUpWithEmail

@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf<String?>(null) }

    fun handleSignUp() {
        if (name.isBlank()) {
            dialogMessage = "Name cannot be empty."
            return
        }

        if (!isValidEmail(email)) {
            dialogMessage = "Invalid email address."
            return
        }

        if (!isValidPassword(password)) {
            dialogMessage = "Password must be at least 6 characters long and include a digit and a special character."
            return
        }

        signUpWithEmail(name, email, password) { success ,  user ->
            if (success) {
                dialogMessage = "Account created successfully!"
                navController.navigate("login") // Navigate to the login screen
            } else {
                dialogMessage = "Signup failed. Please try again."
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bg9),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_hostel),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 54.dp)
                    .height(100.dp)
                    .align(Alignment.Start)
                    .offset(x = (-20).dp)
            )

            Text(
                text = "Sign Up",
                style = TextStyle(
                    fontSize = 29.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
            )

            // Text Fields
            CTextField(hint = "Name", value = name, onValueChange = { name = it })
            CTextField(hint = "Email Address", value = email, onValueChange = { email = it })
            CTextField(hint = "Password", value = password, onValueChange = { password = it })

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                CButton(text = "Sign Up", onClick = { handleSignUp() })
            }

            DontHaveAccountRow(
                text1 = "Already have an account?",
                text2 = " Sign In",
                onTap = {
                    navController.navigate("login")
                }
            )
        }
    }

    // Dialog for feedback
    dialogMessage?.let {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { dialogMessage = null },
            confirmButton = {
                androidx.compose.material3.Button(
                    onClick = { dialogMessage = null }
                ) {
                    Text("OK")
                }
            },
            text = {
                Text(it)
            }
        )
    }
}



@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(rememberNavController())
}