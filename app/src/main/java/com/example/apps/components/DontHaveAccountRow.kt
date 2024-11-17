package com.example.apps.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apps.ui.theme.AlegreyaSansFontFamily

@Composable
fun DontHaveAccountRow(
    text1 : String,
    text2 : String,
    onTap : () -> Unit = {},
){
    Row(
        modifier = Modifier.padding(top = 12.dp, bottom = 52.dp)
    ){
        Text(text = text1,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = AlegreyaSansFontFamily,
                color = Color.White
            )
        )

        Text(text = text2,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = AlegreyaSansFontFamily,
                fontWeight = FontWeight(800)
            ),
            modifier = Modifier.clickable {
                onTap()
            }

        )
    }
}