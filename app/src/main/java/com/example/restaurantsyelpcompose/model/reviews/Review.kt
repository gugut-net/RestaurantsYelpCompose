package com.example.restaurantsyelpcompose.model.reviews


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Review(
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "rating")
    val rating: Int? = 0,
    @Json(name = "text")
    val text: String? = "",
    @Json(name = "time_created")
    val timeCreated: String? = "",
    @Json(name = "url")
    val url: String? = "",
    @Json(name = "user")
    val user: User? = User()
)