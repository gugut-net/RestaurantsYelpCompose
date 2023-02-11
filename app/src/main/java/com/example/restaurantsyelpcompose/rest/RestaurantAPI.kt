package com.example.restaurantsyelpcompose.rest

import com.example.restaurantsyelpcompose.ViewModel.Utils.REVIEWS_PATH
import com.example.restaurantsyelpcompose.ViewModel.Utils.SEARCH_RESTAURANTS
import com.example.restaurantsyelpcompose.model.GeneralResponse
import com.example.restaurantsyelpcompose.model.reviews.ReviewsResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RestaurantAPI {
    @GET(SEARCH_RESTAURANTS)
    suspend fun getRestaurants(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("attributes") atr: String = "hot_and_new",
        @Query("sort_by") sort: String = "best_match",
        @Query("limit") limit: Int = 20
    ) : Response<GeneralResponse>

    @GET(REVIEWS_PATH)
    suspend fun getRestaurantReviews(
        @Path("restId") restId: String
    ):Response<ReviewsResponse>

}

