package com.example.logique_test.domain.mapper

import com.example.logique_test.data.dto.UserDTO
import com.example.logique_test.domain.uimodel.UserUiModel

fun UserDTO.toUiModel() : UserUiModel {
    return UserUiModel(
        id = id ?: "",
        name = title ?: "",
        photoUrl = picture ?: "",
    )
}