package com.example.e_book.ui_layer.Screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.R
import com.example.e_book.ui.theme.Color2
import com.example.e_book.ui_layer.ViewModel.ViewModel
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import java.io.File

@Composable
fun ShowPdfScreen(
    navController: NavHostController, viewModel: ViewModel = hiltViewModel(), url: String
) {


//    val pdfLink = remember { mutableStateOf("") }

    val context = LocalContext.current
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(url),
        isZoomEnable = true
    )
    Column(modifier = Modifier.background(Color.White)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
                        contentDescription = ""
                    )

                }
            }

            Text(
                text = "${pdfState.currentPage}/${pdfState.pdfPageCount}",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins_light))
            )
            Card(
                colors = CardDefaults.cardColors(Color.White),
                border = BorderStroke(1.dp, color = Color.Gray),
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp),
                shape = CircleShape,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            pdfState.file?.let { file ->
                                shareFile(context, file)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "",
                        tint = Color2
                    )

                }
            }
        }


        if (!pdfState.isLoaded) {
            Column(
                modifier = Modifier
                    .fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp),
                    color = Color2,
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Wait for Pdf Loading Done....",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color2,
                    fontFamily = FontFamily(Font(R.font.poppins_light))
                )
            }
        }

        pdfState.isZoomEnable
//        pdfState.isScrolling
        pdfState.isAccessibleEnable
        

        VerticalPDFReader(
            state = pdfState, modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        )

    }



    /*
        val verticalVueReaderState = rememberVerticalVueReaderState(
            resource = VueResourceType.Remote(
                url,
                fileType = VueFileType.PDF
            )
        )

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
                        Button(onClick = {

                        }) {
                        Text(text = "Import Document")
                    }
                }
            }
    */


}


fun shareFile(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "application/pdf"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share PDF"))
}
