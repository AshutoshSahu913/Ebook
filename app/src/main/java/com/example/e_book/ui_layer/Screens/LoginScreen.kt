package com.example.e_book.ui_layer.Screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.e_book.R
import com.example.e_book.ui.theme.Color2
import com.example.e_book.ui.theme.Color3
import com.example.e_book.ui_layer.Navigation.NavigationItem
import com.example.e_book.ui_layer.ViewModel.ViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: ViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var selectedImgUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val name = remember {
        mutableStateOf("")
    }

    val phone = remember {
        mutableStateOf("")
    }

    val errorName = remember {
        mutableStateOf(false)
    }

    val errorPhone = remember {
        mutableStateOf(false)
    }

    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImgUri = uri })

    val verticalScrollableState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollableState)
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(id = R.drawable.img2),
            contentDescription = ""
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            colors = CardDefaults.cardColors(
                Color3
            ),

            ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()

            ) {
                Card(
                    shape = CircleShape,
                    onClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    modifier = Modifier.size(70.dp),
                    elevation = CardDefaults.elevatedCardElevation(5.dp)
                ) {
                    AsyncImage(
                        model = selectedImgUri ?: R.drawable.anonymous,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.anonymous),

                        )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Select Profile", fontStyle = FontStyle.Normal, fontFamily = FontFamily(
                        Font(R.font.poppins_regular)
                    ), fontSize = 15.sp, color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column() {
                        Text(
                            text = "Name", fontStyle = FontStyle.Normal, fontFamily = FontFamily(
                                Font(R.font.poppins_medium)
                            ), fontSize = 15.sp, color = Color.Black
                        )
                        TextField(
                            placeholder = {
                                Text(
                                    text = "John Doe",
                                    fontStyle = FontStyle.Normal,
                                    fontFamily = FontFamily(
                                        Font(R.font.poppins_light)
                                    ),
                                    fontSize = 15.sp,
                                    color = Color.LightGray
                                )
                            },
                            isError = errorName.value,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "",
                                )
                            },
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 15.sp
                            ),
                            value = name.value,
                            onValueChange = { name.value = it },
                            colors = TextFieldDefaults.colors(
                                focusedLeadingIconColor = Color.Blue,
                                unfocusedLeadingIconColor = Color.Gray,
                                focusedLabelColor = Color.Blue,
                                unfocusedLabelColor = Color.Black,
                                focusedContainerColor = Color3,
                                unfocusedContainerColor = Color3,
                                focusedIndicatorColor = Color.Blue,
                                unfocusedIndicatorColor = Color.LightGray,
                                unfocusedPlaceholderColor = Color.Blue,
                                errorTextColor = Color.Red,
                                errorContainerColor = Color3,
                                errorPlaceholderColor = Color.Red,
                                errorLeadingIconColor = Color.Red,
                                errorIndicatorColor = Color.Red,
                                errorLabelColor = Color3,


                                )
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column() {
                        Text(
                            text = "Phone Number",
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(
                                Font(R.font.poppins_medium)
                            ),
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        TextField(
                            placeholder = {
                                Text(
                                    text = "+91 01 2345 6789",
                                    fontStyle = FontStyle.Normal,
                                    fontFamily = FontFamily(
                                        Font(R.font.poppins_light)
                                    ),
                                    fontSize = 15.sp,
                                    color = Color.LightGray
                                )
                            },
                            isError = errorPhone.value,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = "",
                                )
                            },
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 15.sp
                            ),
                            value = phone.value,
                            onValueChange = { number ->
                                if (number.length <= 10) {
                                    phone.value = number
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                focusedLeadingIconColor = Color.Blue,
                                unfocusedLeadingIconColor = Color.Gray,
                                focusedLabelColor = Color.Blue,
                                unfocusedLabelColor = Color.Black,
                                focusedContainerColor = Color3,
                                unfocusedContainerColor = Color3,
                                focusedIndicatorColor = Color.Blue,
                                unfocusedIndicatorColor = Color.LightGray,
                                unfocusedPlaceholderColor = Color.Blue,
                                errorTextColor = Color.Red,
                                errorContainerColor = Color3,
                                errorPlaceholderColor = Color.Red,
                                errorLeadingIconColor = Color.Red,
                                errorIndicatorColor = Color.Red,
                                errorLabelColor = Color3,
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                    ElevatedButton(
                        contentPadding = PaddingValues(horizontal = 100.dp),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .wrapContentSize()

                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        onClick = {
                            errorName.value = false
                            errorPhone.value = false

                            if (name.value.isEmpty() || phone.value.isEmpty()) {
                                if (name.value.isEmpty()) {
                                    errorName.value = true
                                }
                                if (phone.value.isEmpty()) {
                                    errorPhone.value = true
                                }
                                Toast.makeText(context, "Fill all the Fields", Toast.LENGTH_SHORT)
                                    .show()

                            } else {

//                                viewModel.saveUserId(userId = 1)
                                viewModel.saveUserImg(selectedImgUri.toString())
                                viewModel.saveUserName(userName = name.value)
                                viewModel.saveUserPhone(userPhone = phone.value)
                                navController.navigate(NavigationItem.WelcomeScreen)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color2)
                    ) {
                        Text(
                            text = "Login",
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(
                                Font(R.font.poppins_medium)
                            ),
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }

                }


            }
        }

    }
}