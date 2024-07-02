package com.example.e_book.ui_layer.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_book.R
import com.example.e_book.ui.theme.Color1
import com.example.e_book.ui.theme.Color2


@Composable
fun CategoryScreen() {

    val listOfColor = listOf(Color1, Color2)
    val listOfBooks = listOf(R.drawable.book1, R.drawable.book2, R.drawable.book3)
    val brush = Brush.horizontalGradient(listOfColor)
//    Canvas(
//        modifier = Modifier.size(200.dp),
//        onDraw = {
//            drawRect(brush)
////            drawCircle(brush)
//        }
//    )

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(20) {

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(id = listOfBooks.random()),
                        contentDescription = ""
                    )
                    Text(
                        text = "Title $it",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }

}