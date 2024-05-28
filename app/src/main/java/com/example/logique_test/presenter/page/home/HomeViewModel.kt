package com.example.logique_test.presenter.page.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.logique_test.core.data.onFailure
import com.example.logique_test.core.data.onSuccess
import com.example.logique_test.core.uikit.UiState
import com.example.logique_test.domain.repository.UserRepository
import com.example.logique_test.domain.uimodel.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _usersState = MutableStateFlow<PagingData<UserUiModel>>(PagingData.empty())
    val usersState: StateFlow<PagingData<UserUiModel>> = _usersState.asStateFlow()

    fun getUserList() = viewModelScope.launch {
        repository.getUserList().distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _usersState.value = it
            }
    }
}
