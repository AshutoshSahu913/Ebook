package com.example.e_book.ui_layer.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.e_book.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    Column {
        Tabs(pagerState = pagerState)
        TabContent(pagerState = pagerState, navController = navController)
    }
}

@Composable
fun TabContent(pagerState: PagerState, navController: NavHostController) {
    HorizontalPager(state = pagerState) {
        when (it) {
            0 -> {
                CategoryScreen(modifier = Modifier.fillMaxSize(),navController=navController)
            }
            1 -> {
                BookScreen(modifier = Modifier.fillMaxSize(),navController=navController)
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val customCoroutineScope = rememberCoroutineScope()
    val tabNames = arrayOf(
        TabLayoutItem(R.drawable.category, "Category"),
        TabLayoutItem(R.drawable.books, "Books")
    )

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.wrapContentSize(),
        containerColor = Color.White,

    ) {
        tabNames.forEachIndexed { index, tabItem ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    customCoroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    if (pagerState.currentPage == index) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color.Black,
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                painter = painterResource(id = tabItem.itemIcon),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = tabItem.title,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            )

                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .clip(
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {

                            Icon(
                                painter = painterResource(id = tabItem.itemIcon),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = tabItem.title,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            )

                        }
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White),
            )
        }
    }
}

data class TabLayoutItem(var itemIcon: Int, var title: String)