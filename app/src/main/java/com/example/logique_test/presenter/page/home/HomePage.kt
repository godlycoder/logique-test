package com.example.logique_test.presenter.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.logique_test.R
import com.example.logique_test.core.uikit.UiState
import com.example.logique_test.core.uikit.component.CardUser
import com.example.logique_test.core.uikit.component.ErrorMessage
import com.example.logique_test.core.uikit.component.LoadingNextPageItem
import com.example.logique_test.presenter.navigation.Actions

@Composable
fun HomePage(
    actions: Actions? = null
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    viewModel.getUserList()

    val usersData = viewModel.usersState.collectAsLazyPagingItems()

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier
                .height(80.dp)
                .width(80.dp),
            onClick = actions?.favorite ?: {},
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        )
    }) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(innerPadding),
            columns = GridCells.Fixed(2),
        ) {
            items(usersData.itemCount) {index ->
                val item = usersData[index]
                CardUser(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = { actions?.user(item?.id ?: "") }),
                    url = item?.photoUrl ?: "",
                    title = item?.name ?: "",
                    description = item?.id ?: "",
                )
            }

            when {
                usersData.loadState.refresh is LoadState.Loading -> {
                    items(10) {
                        Card(
                            modifier = Modifier.aspectRatio(6f / 7f)
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Gray),
                            shape = MaterialTheme.shapes.medium,
                        ) {}
                    }
                }

                usersData.loadState.refresh is LoadState.Error -> {
                    val error = usersData.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { viewModel.getUserList()  })
                    }
                }

                usersData.loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                usersData.loadState.append is LoadState.Error -> {
                    val error = usersData.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage ?: "",
                            onClickRetry = { viewModel.getUserList() })
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun HomePagePreview() {
    HomePage()
}