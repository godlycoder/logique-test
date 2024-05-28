package com.example.logique_test.presenter.page.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.logique_test.core.data.onFailure
import com.example.logique_test.core.data.onSuccess
import com.example.logique_test.core.uikit.UiState
import com.example.logique_test.domain.repository.PostRepository
import com.example.logique_test.domain.repository.ProfileRepository
import com.example.logique_test.domain.uimodel.PostUiModel
import com.example.logique_test.domain.uimodel.ProfileUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _profileState = MutableStateFlow<UiState<ProfileUiModel>>(UiState.Initial)
    val profileState = _profileState.asStateFlow()

    private val _postsState = MutableStateFlow<PagingData<PostUiModel>>(PagingData.empty())
    val postsState: StateFlow<PagingData<PostUiModel>> = _postsState.asStateFlow()

    private val _likeState = MutableStateFlow<UiState<Boolean>>(UiState.Initial)
    val likeState = _likeState.asStateFlow()


    fun getProfile(userId: String) = viewModelScope.launch {
        _profileState.value = UiState.Loading

        profileRepository.getUserProfile(userId).collect { state ->
            state.onSuccess {
                _profileState.value = UiState.Success(it)
            }.onFailure {
                _profileState.value = UiState.Error(it.message.toString())
            }
        }
    }

    fun getUserPost(userId: String) = viewModelScope.launch {
        postRepository.getUserPosts(userId).distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _postsState.value = it
            }
    }

    fun likePost(model: PostUiModel, like: Boolean) = viewModelScope.launch {
        _likeState.value = UiState.Loading

        postRepository.likePost(model, like).collect { state ->
            state.onSuccess {
                _likeState.value = UiState.Success(it)
            }.onFailure {
                _likeState.value = UiState.Error(it.message.toString())
            }
        }
    }

}