package com.example.e_book.ui_layer.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.e_book.R
import com.example.e_book.ui.theme.Color1
import com.example.e_book.ui.theme.Color2
import com.example.e_book.ui_layer.Navigation.NavigationItem
import com.example.e_book.ui_layer.ViewModel.ViewModel


@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel = hiltViewModel(),
    navController: NavHostController
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllCategories()
    }

    val res = viewModel.state.value

    val listOfColor = listOf(Color1, Color2)
    when {
        res.isLoading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    trackColor = Color.Black, strokeWidth = 2.dp, color = Color.White
                )
            }
        }

        res.error.isNotEmpty() -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.error),
                    contentDescription = "",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = res.error,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    color = Color.Black
                )
            }
        }

        res.category.isNotEmpty() -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(res.category) {
                    Log.d("CATEGORY", "CategoryScreen 1: ${res.category} ")

                    Column(modifier = Modifier
                        .clickable {
                            navController.navigate(NavigationItem.CategoryScreen(category = it.Name))
                        }
                        .fillMaxSize()
                        .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween) {

                        Card(
                            modifier = Modifier.padding(5.dp),
                            colors = CardDefaults.cardColors(Color.White),
                        ) {
                            AsyncImage(
                                modifier = Modifier.wrapContentSize(),
                                model = it.img,
                                contentScale = ContentScale.Fit,
                                contentDescription = ""
                            )
                        }

                        Text(
                            text = it.Name.uppercase(),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),

                        )
                    }
                }
            }

        }
    }
}