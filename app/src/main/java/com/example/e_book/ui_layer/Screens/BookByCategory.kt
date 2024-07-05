package com.example.e_book.ui_layer.Screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.e_book.R
import com.example.e_book.ui.theme.Color2
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
//    Log.d("BOOK", "BookScreen 2 :------------------${res.books} ")

    Column {
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color.White),
                border = BorderStroke(1.dp, color = Color.Gray),
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            navController.navigateUp()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "", tint = Color.Black
                    )
                }
            }
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                text = category,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        when {
            res.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        trackColor = Color.Black, strokeWidth = 2.dp, color = Color.White
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Wait for Book Loading Done....",
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color2,
                        fontFamily = FontFamily(Font(R.font.poppins_light))
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                        items(res.books) { book ->
//                            Log.d("BOOK", "BookScreen 1:------------------${book.bookName} ")
                            Column(
                                modifier = Modifier.wrapContentSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Card(
                                    onClick = {
                                        navController.navigate(NavigationItem.ShowPdfScreen(book.bookPdfUrl))
                                    },
                                    modifier = Modifier.padding(10.dp),
                                    shape = RoundedCornerShape(1.dp),
                                    colors = CardDefaults.cardColors(
                                        Color.White
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .padding(5.dp),
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
                                                .background(Color.White)
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

            res.books.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_book),
                        contentDescription = "",
                        modifier = Modifier.size(200.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "No Book for This Category",
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.poppins_light))

                    )
                }
            }
        }
    }

}