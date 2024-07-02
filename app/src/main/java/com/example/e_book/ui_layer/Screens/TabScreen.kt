package com.example.e_book.ui_layer.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.e_book.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState(pageCount = { 2 })
    Column {
        Tabs(pagerState = pagerState)
        TabContent(pagerState = pagerState)

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) {
        when (it) {
            0 -> {
                CategoryScreen()
            }

            1 -> {
                BookScreen(modifier = Modifier.fillMaxSize())
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
        selectedTabIndex = pagerState.currentPage
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
                    Text(text = tabItem.title)
                },
                icon = {
                    Icon(painter = painterResource(id = tabItem.itemIcon), contentDescription = "")
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                modifier = Modifier.background(Color.White),
            )
        }
    }
}

data class TabLayoutItem(var itemIcon: Int, var title: String)