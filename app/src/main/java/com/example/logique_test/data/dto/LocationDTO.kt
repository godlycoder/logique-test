package com.example.logique_test.data.dto

import com.google.gson.annotations.SerializedName


data class LocationDTO (

  @SerializedName("street"   ) var street   : String? = null,
  @SerializedName("city"     ) var city     : String? = null,
  @SerializedName("state"    ) var state    : String? = null,
  @SerializedName("country"  ) var country  : String? = null,
  @SerializedName("timezone" ) var timezone : String? = null

)