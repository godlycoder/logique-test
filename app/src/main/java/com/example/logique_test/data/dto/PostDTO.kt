package com.example.logique_test.data.dto

import com.google.gson.annotations.SerializedName


data class PostDTO (

  @SerializedName("id"          ) var id          : String?           = null,
  @SerializedName("image"       ) var image       : String?           = null,
  @SerializedName("likes"       ) var likes       : Int?              = null,
  @SerializedName("tags"        ) var tags        : ArrayList<String> = arrayListOf(),
  @SerializedName("text"        ) var text        : String?           = null,
  @SerializedName("publishDate" ) var publishDate : String?           = null,
  @SerializedName("owner"       ) var owner       : OwnerDTO?         = OwnerDTO()

)