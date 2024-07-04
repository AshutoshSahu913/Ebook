package com.example.e_book.ui_layer.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(content = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
        ) {
            TabLayout(navController)
        }
    }, topBar = {

        Card(
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .height(80.dp)
                        .padding(start = 10.dp), verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(modifier = Modifier.wrapContentSize()) {
                        Text(
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .align(Alignment.Bottom),
                            text = "Hello 🫵 ",
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Black,
//                            fontFamily = FontFamily(Font(R.font.poppins_light)),
                            fontSize = 25.sp
                        )
                        Text(
                            modifier = Modifier.align(Alignment.Top),
                            text = "Ashu",
                            color = Color.Blue,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Black,
//                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 25.sp
                        )

                    }
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = "Welcome To The Library",
                        color = Color.Black,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Light,
//                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontSize = 20.sp,
                    )
                }

            }
        }
    }
    )
}