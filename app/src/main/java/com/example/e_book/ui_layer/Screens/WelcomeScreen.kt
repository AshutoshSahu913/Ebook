package com.example.e_book.ui_layer.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.R
import com.example.e_book.ui_layer.Navigation.NavigationItem
import com.example.e_book.ui_layer.ViewModel.ViewModel

@Composable
fun WelcomeScreen(navController: NavHostController, viewModel: ViewModel = hiltViewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.img1),
            contentDescription = "",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            text = "Start your\nJourney with Books",
            fontWeight = FontWeight.Normal,
            fontSize = 25.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            textAlign = TextAlign.Center, color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Let's get you Started",
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.poppins_light)),
        )

        Spacer(modifier = Modifier.height(20.dp))

        ElevatedButton(
            modifier = Modifier,
            onClick = {
                viewModel.saveUserId(userId = 1)
                navController.navigate(NavigationItem.HomeScreen)
            },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            shape = CircleShape,

            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 10.dp)
        ) {

            Text(
                text = "Get Started ",
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
            )
        }

    }

}