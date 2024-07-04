package com.example.e_book.ui_layer.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.ui_layer.ViewModel.ViewModel
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun ShowPdfScreen(
    navController: NavHostController, viewModel: ViewModel = hiltViewModel(), url: String
) {

    val pdfLink = remember { mutableStateOf("") }

    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(url), isZoomEnable = true
    )

    val verticalPdfReaderState = pdfState.isLoaded

//    while (pdfState.pdfPageCount < 100) {
        CircularProgressIndicator(
            progress = { pdfState.loadPercent.toFloat() },
        )
//    }
//    if (pdfState.isLoaded) {
        VerticalPDFReader(
            state = pdfState, modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        )
//    }

//    pdfState.isLoaded


//    val verticalVueReaderState = rememberVerticalVueReaderState(
//        resource = VueResourceType.Remote(
//            url,
//            fileType = VueFileType.PDF
//        ), cache = 10


//    )
    /*
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
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error loading document", color = Color.Red)
                }
            }

            VueLoadState.DocumentImporting -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Importing document...", color = Color.Gray)
                }
            }

            VueLoadState.DocumentLoaded -> {
                VerticalVueReader(
                    modifier = Modifier.fillMaxSize(),
                    contentModifier = Modifier.fillMaxWidth(),
                    verticalVueReaderState = verticalVueReaderState
                )
            }

            VueLoadState.NoDocument -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Button(onClick = { *//* Launch import intent *//*
 }) {
                    Text(text = "Import Document")
                }
            }
        }
    }
*/
}