package com.example.e_book.ui_layer.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.e_book.R
import com.example.e_book.ui_layer.Navigation.NavigationItem
import com.example.e_book.ui_layer.ViewModel.ViewModel

@Composable
fun BookByCategory(
    viewModel: ViewModel = hiltViewModel(),
    category: String,
    navController: NavHostController
) {


    LaunchedEffect(key1 = true) {
        viewModel.getBooksByCategory(category)
    }
    val res = viewModel.state.value
    Log.d("BOOK", "BookScreen 2 :------------------${res.books} ")


    when {
        res.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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

        res.books.isNotEmpty() -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(res.books) { book ->
                    Log.d("BOOK", "BookScreen 1:------------------${book.bookName} ")
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,

                        ) {
                        ElevatedCard(
                            onClick = {
                                navController.navigate(NavigationItem.ShowPdfScreen(book.bookPdfUrl))
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp),
                            shape = RectangleShape,
                            colors = CardDefaults.cardColors(Color.White),
                            elevation = CardDefaults.elevatedCardElevation(1.dp)
                        ) {

                            AsyncImage(
                                modifier = Modifier
                                    .size(200.dp),
                                contentScale = ContentScale.Fit,
                                placeholder = painterResource(id = R.drawable.book1),
                                model = book.bookImgUrl,
                                contentDescription = ""
                            )
                        }

                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            text = book.bookName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )

                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            text = book.bookAuthor,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                        )
                    }
                }
            }
        }
    }
}