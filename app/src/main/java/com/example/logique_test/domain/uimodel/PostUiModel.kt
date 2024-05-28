package com.example.logique_test.domain.uimodel

class PostUiModel(
    val imageUrl: String,
    val title: String,
    val desc: String,
    val tags: List<String>,
    val likesCount: Int,
    val isLike: Boolean,
    val id: String
) {

}
