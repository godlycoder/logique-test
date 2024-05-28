package com.example.logique_test.domain.mapper

import com.example.logique_test.data.dto.ProfileDTO
import com.example.logique_test.domain.uimodel.ProfileUiModel

fun ProfileDTO.toUiModel() = ProfileUiModel(
    name = title ?: "",
    photoUrl = picture ?: "",
    id = id ?: "",
    email = email ?: ""
)