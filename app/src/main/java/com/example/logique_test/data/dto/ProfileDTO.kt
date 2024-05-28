package com.example.logique_test.data.dto

import com.google.gson.annotations.SerializedName


data class ProfileDTO (

  @SerializedName("id"           ) var id           : String?   = null,
  @SerializedName("title"        ) var title        : String?   = null,
  @SerializedName("firstName"    ) var firstName    : String?   = null,
  @SerializedName("lastName"     ) var lastName     : String?   = null,
  @SerializedName("picture"      ) var picture      : String?   = null,
  @SerializedName("gender"       ) var gender       : String?   = null,
  @SerializedName("email"        ) var email        : String?   = null,
  @SerializedName("dateOfBirth"  ) var dateOfBirth  : String?   = null,
  @SerializedName("phone"        ) var phone        : String?   = null,
  @SerializedName("location"     ) var location     : LocationDTO? = LocationDTO(),
  @SerializedName("registerDate" ) var registerDate : String?   = null,
  @SerializedName("updatedDate"  ) var updatedDate  : String?   = null

)