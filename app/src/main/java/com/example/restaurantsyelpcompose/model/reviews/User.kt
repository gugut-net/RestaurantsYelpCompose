package com.example.restaurantsyelpcompose.model.reviews


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "image_url")
    val imageUrl: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "profile_url")
    val profileUrl: String? = ""
)