package com.example.restaurantsyelpcompose.model.reviews


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewsResponse(
    @Json(name = "possible_languages")
    val possibleLanguages: List<String?>? = listOf(),
    @Json(name = "reviews")
    val reviews: List<Review?>? = listOf(),
    @Json(name = "total")
    val total: Int? = 0
)