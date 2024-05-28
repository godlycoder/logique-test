package com.example.logique_test.domain.mapper

import com.example.logique_test.data.dto.PostDTO
import com.example.logique_test.data.services.local.entity.PostEntity
import com.example.logique_test.domain.uimodel.PostUiModel

fun PostDTO.toUiModel() : PostUiModel {
    return PostUiModel(
        imageUrl = image ?: "",
        title = owner?.title ?: "",
        desc = text ?: "",
        tags = tags,
        likesCount = likes ?: 0,
        isLike = false,
        id = id ?: "",
    )
}

fun PostEntity.toUiModel() : PostUiModel {
    return PostUiModel(
        imageUrl = imageUrl,
        title = title,
        desc = desc ?: "",
        tags = tags,
        likesCount = likesCount ?: 0,
        isLike = isLike,
        id = postId ?: "",
    )
}

fun PostUiModel.toEntity() : PostEntity {
    return PostEntity(
        imageUrl = imageUrl,
        title = title,
        desc = desc,
        tags = tags,
        postId = id,
        likesCount = likesCount,
        isLike = isLike,
    )
}