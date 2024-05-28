package com.example.logique_test.presenter.page.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logique_test.core.uikit.UiState
import com.example.logique_test.core.uikit.component.CardPost
import com.example.logique_test.core.uikit.component.ErrorMessage
import com.example.logique_test.core.uikit.component.LoadingNextPageItem
import com.example.logique_test.domain.uimodel.PostUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritePage() {
    val viewModel = hiltViewModel<FavoriteViewModel>()

    viewModel.getPosts()
    val posts = viewModel.posts.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text("Favorite")
            }
        )

    }) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(innerPadding),
            columns = GridCells.Fixed(2),
        ) {
            when(posts.value) {
                is UiState.Error -> {
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillMaxSize(),
                            message = (posts.value as UiState.Error).message,
                            onClickRetry = { viewModel.getPosts()  },
                        )
                    }
                }
                is UiState.Loading -> {
                    item {
                        LoadingNextPageItem(modifier = Modifier)
                    }
                }
                is UiState.Success -> {
                    items((posts.value as UiState.Success<List<PostUiModel>>).data.size) { index ->
                        val item = (posts.value as UiState.Success<List<PostUiModel>>).data[index]
                        CardPost(
                            url = item.imageUrl,
                            title = item.title,
                            description = item.desc,
                            tags = item.tags,
                            likesCount = item.likesCount,
                            isLiked = item.isLike
                        ) {

                        }
                    }
                }
                else -> {}
            }
        }
    }
}