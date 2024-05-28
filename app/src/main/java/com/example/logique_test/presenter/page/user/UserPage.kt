package com.example.logique_test.presenter.page.user

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.logique_test.core.uikit.UiState
import com.example.logique_test.core.uikit.component.CardPost
import com.example.logique_test.core.uikit.component.CardUser
import com.example.logique_test.core.uikit.component.ErrorMessage
import com.example.logique_test.core.uikit.component.LoadingNextPageItem
import com.example.logique_test.presenter.navigation.Actions

@Composable
fun UserPage(
    userId: String,
    actions: Actions? = null
) {
    val viewModel = hiltViewModel<UserViewModel>()
    viewModel.getProfile(userId)
    viewModel.getUserPost(userId)

    val profileState = viewModel.profileState.collectAsState()
    val postData = viewModel.postsState.collectAsLazyPagingItems()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {

            when (profileState.value) {
                is UiState.Loading -> {
                    Text(text = "Loading")
                }

                is UiState.Error -> {
                    Text(text = (profileState.value as UiState.Error).message)
                }

                is UiState.Success -> {
                    val data = (profileState.value as UiState.Success).data
                    ProfileHeader(
                        name = data.name,
                        photoUrl = data.photoUrl
                    )
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        TextButton(onClick = {}) {
                            Text(text = data.id)
                        }
                        TextButton(onClick = {}) {
                            Text(text = data.email)
                        }
                    }
                }

                else -> {}
            }

            LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(2)) {

                if (postData.itemCount == 0) {
                    item {
                        Box(modifier = Modifier.fillMaxSize().padding(20.dp),
                            contentAlignment = Alignment.Center,) {
                            Text(text = "No Post")
                        }
                    }
                }

                items(postData.itemCount) {index ->
                    val item = postData[index]
                    CardPost(
                        url = item?.imageUrl ?: "",
                        title = item?.title ?: "",
                        description = item?.desc ?: "",
                        tags = item?.tags ?: listOf(),
                        likesCount = item?.likesCount ?: 0,
                        isLiked = item?.isLike ?: false
                    ) {
                        item?.let {
                            viewModel.likePost(item, item.isLike)
                        }

                    }
                }

                when {
                    postData.loadState.refresh is LoadState.Loading -> {
                        items(10) {
                            Card(
                                modifier = Modifier
                                    .aspectRatio(6f / 7f)
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.Gray),
                                shape = MaterialTheme.shapes.medium,
                            ) {}
                        }
                    }

                    postData.loadState.refresh is LoadState.Error -> {
                        val error = postData.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier.fillMaxSize(),
                                message = error.error.localizedMessage!!,
                                onClickRetry = { viewModel.getUserPost(userId)  })
                        }
                    }

                    postData.loadState.append is LoadState.Loading -> {
                        item { LoadingNextPageItem(modifier = Modifier) }
                    }

                    postData.loadState.append is LoadState.Error -> {
                        val error = postData.loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier,
                                message = error.error.localizedMessage ?: "",
                                onClickRetry = { viewModel.getUserPost(userId) })
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun ProfileHeader(
    name: String,
    photoUrl: String
) {

    val painter = rememberImagePainter(
        data = photoUrl,
        builder = {
            crossfade(true)
        }
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Profile picture and username
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
@Preview
fun ProfilePagePreview() {
    UserPage(
    userId = ""
    )
}