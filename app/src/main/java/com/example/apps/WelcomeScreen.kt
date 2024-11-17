package com.example.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.apps.components.DontHaveAccountRow
import com.example.apps.ui.theme.AlegreyaFontFamily
import com.example.apps.ui.theme.AlegreyaSansFontFamily

@Composable
fun WelcomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
){

    Box(
        modifier = modifier.fillMaxSize()
    ){
        //Background Image
        Image(painter = painterResource(id = R.drawable.bg9),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()

        )

        //Content
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ){
            Spacer(modifier = Modifier.weight(1f))

            Image(painter = painterResource(id = R.drawable.logo_hostel),
                contentDescription = null,
                modifier = Modifier
                    .width(300.dp)
                    .height(220.dp),
                contentScale = ContentScale.Fit
            )

            Text("WELCOME",
                fontSize = 32.sp,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight(700),
                color = Color.White

            )

            Spacer(modifier = Modifier.weight(2f))

            //Button
           CButton(text = "Sign In",
               onClick = {
                   navController.navigate("login")
               })

            //SignUp Text
            DontHaveAccountRow(
                text1 = "Don't have an account?" ,
                text2 = " Sign Up",
                onTap = {
                    navController.navigate("signup")
                }
            )
        }

    }

}


@Preview(showBackground = true, widthDp = 320, heightDp = 620)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(rememberNavController())
}