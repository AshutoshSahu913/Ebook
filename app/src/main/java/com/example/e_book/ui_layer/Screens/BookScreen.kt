package com.example.e_book.ui_layer.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import com.example.e_book.ui.theme.Color3
import com.example.e_book.ui_layer.Navigation.NavigationItem
import com.example.e_book.ui_layer.ViewModel.ViewModel

@Composable
fun BookScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel = hiltViewModel(),
    navController: NavHostController
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllBooks()
    }
    val res = viewModel.state.value

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

        res.books.isNotEmpty() -> {
//            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            LazyColumn {
                items(res.books) { book ->
                    Log.d("BOOK", "BookScreen 1:------------------${book.bookName} ")
                    Card(
                        onClick = {
                            navController.navigate(NavigationItem.ShowPdfScreen(book.bookPdfUrl))
                        }, modifier = Modifier.padding(10.dp), shape = RoundedCornerShape(1.dp),

                        colors = CardDefaults.cardColors(
                            Color3
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
//                            ElevatedCard(
//                                modifier = Modifier.size(200.dp)
//                                ,
//                                colors = CardDefaults.cardColors(Color.White),
//
//                            ) {

                            AsyncImage(
                                modifier = Modifier
                                    .background(Color3)
                                    .size(200.dp),
                                contentScale = ContentScale.None,
                                placeholder = painterResource(id = R.drawable.book1),
                                model = book.bookImgUrl,
                                contentDescription = ""
                            )
//                            }

                            Column {
                                Text(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    text = book.bookName,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                )
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.End)
                                        .padding(horizontal = 10.dp),
                                    text = "-${book.bookAuthor}",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                )

                            }

                        }
                    }

                    HorizontalDivider(color = Color.LightGray)
                }
                }
            }

    }
}
