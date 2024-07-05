package com.example.e_book.ui_layer.Screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.e_book.R
import com.example.e_book.ui.theme.Color2
import com.example.e_book.ui.theme.Color3
import com.example.e_book.ui_layer.Navigation.NavigationItem

@Composable
fun HomeScreen(
    navController: NavHostController,
    userId: Int,
    userName: String,
    userPhone: String,
    userImg: String
) {
    Scaffold(
        content = {
            Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
        ) {
                Card(
                    colors = CardDefaults.cardColors(Color3),
                    elevation = CardDefaults.cardElevation(2.dp),
                    modifier = Modifier.padding(top = 10.dp),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                ) {
                    TabLayout(navController)
                }
        }
    }, topBar = {
//
//        Card(
//            colors = CardDefaults.cardColors(Color.White),
//            elevation = CardDefaults.cardElevation(2.dp),
//            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
//        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
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
                            text = "Hello, ",
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,

                            fontFamily = FontFamily(Font(R.font.poppins_light)),
                            fontSize = 25.sp,
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.Bottom),
                            text = userName,
                            color = Color2,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            fontSize = 30.sp
                        )

                    }
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = "Welcome To The Library \uD83D\uDCDA",
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.None
                    )
                }
                Card(
                    shape = CircleShape,
                    onClick = {
                        navController.navigate(NavigationItem.ProfileScreen)
                    },
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .size(50.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    elevation = CardDefaults.elevatedCardElevation(5.dp)
                ) {
//                    Log.d("HOME_SCREEN", "HomeScreen: $userImg")
                    AsyncImage(
                        model = userImg,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.anonymous),
                        error = painterResource(id = R.drawable.anonymous)
                    )
                }
//            }
        }
    }
    )
}