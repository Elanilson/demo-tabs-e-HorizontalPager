package br.com.apkdoandroid.tabsehorizontalpage

import android.graphics.Paint.Align
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsIU(
    modifier : Modifier = Modifier
) {
    var selectedTabIndex by remember{ mutableStateOf(0) }
    var pageState = rememberPagerState(initialPage = TabNavItem.items.size)

    LaunchedEffect(selectedTabIndex){
        pageState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pageState.currentPage,pageState.isScrollInProgress){
        if(!pageState.isScrollInProgress){
            selectedTabIndex = pageState.currentPage
        }
    }

    Column( modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = {tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .padding(horizontal = 32.dp)
                        .clip(CircleShape),
                    color = Color.Black

                )
            },
            divider = {},
            modifier = modifier,
            contentColor = Color.Gray,
            tabs = {
                TabNavItem.items.forEachIndexed { index, tabNavItem ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = stringResource(id = tabNavItem.title))},
                        icon = {
                            Icon(painter = if(selectedTabIndex == index){
                                painterResource(id = tabNavItem.selectedIcon)
                            }else{
                                painterResource(id = tabNavItem.unSelectedIcon)
                            }
                                , contentDescription = null)
                        }

                    )

                }
            }
        )

        HorizontalPager(
            pageCount = TabNavItem.items.size,
            state = pageState,
            pageContent = { tabPosition ->
                val tab = TabNavItem.items[tabPosition]

                when(tab.type){
                    TabNavItem.TabNavType.HOME -> {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = stringResource(id = tab.title))
                        }
                    }
                    TabNavItem.TabNavType.SEARCH -> {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = stringResource(id = tab.title))
                        }
                    }
                    TabNavItem.TabNavType.NOTIFICATION -> {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = stringResource(id = tab.title))
                        }
                    }
                }

            }
        )
    }



}

@Preview(showBackground =  true)
@Composable
fun TabsIUPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabsIU()

    }

}