package com.example.apps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apps.components.CButton
import com.example.apps.ui.theme.AlegreyaFontFamily
import com.example.apps.ui.theme.AlegreyaSansFontFamily


@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Reset Password",
            style = TextStyle(
                fontSize = 29.sp,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight(500),
                color = Color.Black,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enter your Email address and we will send you instructions to reset your password.",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = AlegreyaSansFontFamily,
//                fontWeight = FontWeight(500),
                color = Color.Black,
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        CButton(
            text = "Continue",
            onClick = {
                sendPasswordResetEmail(email) { success ->
                    if (success) {
                        successMessage = "Password reset email sent successfully!"
                        errorMessage = null
                    } else {
                        successMessage = null
                        errorMessage = "Failed to send reset email. Please try again."
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        successMessage?.let {
            Text(text = it, color = Color.Green)
        }

        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 620)
@Composable
fun ForgotPasswordScreenPreview(){
    ForgotPasswordScreen(rememberNavController())
}