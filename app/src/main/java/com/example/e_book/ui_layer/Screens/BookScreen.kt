package com.example.e_book.ui_layer.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.e_book.R
import com.example.e_book.ui_layer.ViewModel.ViewModel
import com.pratikk.jetpdfvue.VerticalVueReader
import com.pratikk.jetpdfvue.state.VueFileType
import com.pratikk.jetpdfvue.state.VueLoadState
import com.pratikk.jetpdfvue.state.VueResourceType
import com.pratikk.jetpdfvue.state.rememberVerticalVueReaderState


@Composable
fun BookScreen(modifier: Modifier, viewModel: ViewModel = hiltViewModel()) {
    val listOfBooks = listOf(R.drawable.book1, R.drawable.book2, R.drawable.book3)
    val pdfLink = remember {
        mutableStateOf("")
    }

    val res = viewModel.state.value
    if (pdfLink.value.isNullOrEmpty()) {

        if (res.isLoading) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    trackColor = Color.Black,
                    strokeWidth = 2.dp,
                    color = Color.White
                )
            }
        }
        if (res.error.isNotEmpty()) {
            Column(
                modifier = modifier.fillMaxSize(),
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
        if (res.books.isNotEmpty()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(res.books) {
                    ElevatedCard(
                        onClick = {
                            pdfLink.value = it.bookPdfUrl
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(1.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.Fit,
                                placeholder = painterResource(id = R.drawable.book1),
                                model = it.bookImgUrl, contentDescription = ""
                            )

                            Text(
                                modifier = Modifier
                                    .background(Color.Black)
                                    .padding(10.dp),
                                text = it.bookName,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
    } else {

        val verticalVueReaderState = rememberVerticalVueReaderState(
            resource = VueResourceType.Local(
                uri = pdfLink.value.toUri(),
                fileType = VueFileType.PDF
            ),
            cache = 3 // By default 0
        )
// .toFile is an util extension function to convert any input stream to a file


        val vueLoadState = verticalVueReaderState.vueLoadState
        when (vueLoadState) {
            is VueLoadState.DocumentLoading -> {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    trackColor = Color.Black
                )
            }

            is VueLoadState.DocumentError -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Error")
                }
            }

            VueLoadState.DocumentImporting -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Importing...")
                }

            }

            VueLoadState.DocumentLoaded -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    VerticalVueReader(
                        modifier = Modifier, // Modifier for pager
                        contentModifier = Modifier, // Modifier for Individual page
                        verticalVueReaderState = verticalVueReaderState
                    )
                }
            }

            VueLoadState.NoDocument -> {
                Text(text = "No Documents")
            }
        }

        /*
        val pdfState = rememberVerticalPdfReaderState(
            resource = ResourceType.Remote(pdfLink.value),
            isZoomEnable = true,
        )

        Column(modifier = Modifier.fillMaxSize()) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                trackColor = Color.Black
            )
            VerticalPDFReader(
                state = pdfState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray)
            )
        }*/

    }
}