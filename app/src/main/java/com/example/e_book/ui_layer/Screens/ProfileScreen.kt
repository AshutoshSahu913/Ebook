package com.example.e_book.ui_layer.Screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Logout
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.e_book.R
import com.example.e_book.ui.theme.Color2
import com.example.e_book.ui.theme.Color3
import com.example.e_book.ui_layer.Navigation.NavigationItem
import com.example.e_book.ui_layer.ViewModel.ViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    userId: Int,
    userName: String,
    userPhone: String,
    userImg: String,
    viewModel: ViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var selectedImgUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var name by rememberSaveable { mutableStateOf(userName) }
    var phone by rememberSaveable { mutableStateOf(userPhone) }

    var errorName by rememberSaveable { mutableStateOf(false) }
    var errorPhone by rememberSaveable { mutableStateOf(false) }

    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImgUri = uri })

    val verticalScrollableState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollableState)
            .background(Color3)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
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
                        }, contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                painter = painterResource(id = R.drawable.img2),
                contentDescription = ""
            )

            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color.White),
                border = BorderStroke(1.dp, color = Color.Gray),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .size(40.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            viewModel.deleteUserId()
                            viewModel.deleteUserName()
                            viewModel.deleteUserPhone()
                            viewModel.deleteUserImg()
                            navController.navigate(NavigationItem.LoginScreen)
                            navController.popBackStack()
                        }, contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = ""
                    )
                }
            }

        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            colors = CardDefaults.cardColors(Color.White),
        ) {
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "My Profile",
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
                )
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    shape = CircleShape,
                    onClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier.size(70.dp),
                    elevation = CardDefaults.elevatedCardElevation(5.dp)
                ) {
                    AsyncImage(
                        model = selectedImgUri ?: userImg,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        error = painterResource(id = R.drawable.anonymous),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.anonymous),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Select Profile",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 15.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column {
                        Text(
                            text = "Name",
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        TextField(
                            placeholder = {
                                Text(
                                    text = "John Doe",
                                    fontStyle = FontStyle.Normal,
                                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                                    fontSize = 15.sp,
                                    color = Color.LightGray
                                )
                            },
                            isError = errorName,
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
                            value = name,
                            onValueChange = { name = it },
                            colors = TextFieldDefaults.colors(
                                focusedLeadingIconColor = Color2,
                                unfocusedLeadingIconColor = Color.Gray,
                                focusedLabelColor = Color2,
                                unfocusedLabelColor = Color.Black,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color2,
                                unfocusedIndicatorColor = Color.LightGray,
                                unfocusedPlaceholderColor = Color2,
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
                    Column {
                        Text(
                            text = "Phone Number",
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        TextField(
                            placeholder = {
                                Text(
                                    text = "+91 01 2345 6789",
                                    fontStyle = FontStyle.Normal,
                                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                                    fontSize = 15.sp,
                                    color = Color.LightGray
                                )
                            },
                            isError = errorPhone,
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
                            value = phone,
                            onValueChange = { number ->
                                if (number.length <= 10) {
                                    phone = number
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                focusedLeadingIconColor = Color2,
                                unfocusedLeadingIconColor = Color.Gray,
                                focusedLabelColor = Color2,
                                unfocusedLabelColor = Color.Black,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color2,
                                unfocusedIndicatorColor = Color.LightGray,
                                unfocusedPlaceholderColor = Color2,
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
                            errorName = false
                            errorPhone = false

                            if (name.isEmpty() || phone.isEmpty()) {
                                if (name.isEmpty()) {
                                    errorName = true
                                }
                                if (phone.isEmpty()) {
                                    errorPhone = true
                                }
                                Toast.makeText(context, "Fill all the Fields", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                viewModel.saveUserImg(selectedImgUri.toString())
                                viewModel.saveUserName(userName = name)
                                viewModel.saveUserPhone(userPhone = phone)
                                Toast.makeText(context, "Saved Details !", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color2)
                    ) {
                        Text(
                            text = "Save",
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
