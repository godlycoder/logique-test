package com.example.logique_test.presenter.page.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logique_test.core.data.onFailure
import com.example.logique_test.core.data.onSuccess
import com.example.logique_test.core.uikit.UiState
import com.example.logique_test.domain.repository.PostRepository
import com.example.logique_test.domain.uimodel.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _posts = MutableStateFlow<UiState<List<PostUiModel>>>(UiState.Initial)
    val posts = _posts.asStateFlow()

    fun getPosts() = viewModelScope.launch {
        _posts.value = UiState.Loading

        repository.getFavoritesPost().collect { state ->
            state.onSuccess {
                _posts.value = UiState.Success(it)
            }.onFailure {
                _posts.value = UiState.Error(it.message.toString())
            }
        }
    }
}