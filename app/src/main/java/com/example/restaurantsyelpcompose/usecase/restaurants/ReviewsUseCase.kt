package com.example.restaurantsyelpcompose.usecase.restaurants

import com.example.restaurantsyelpcompose.ViewModel.Utils.UIState
import com.example.restaurantsyelpcompose.rest.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewsUseCase @Inject constructor(
    private val repository: Repository
) {

    fun getReviews(restId: String): Flow<UIState> = repository.getRestaurantDetails(restId)
}