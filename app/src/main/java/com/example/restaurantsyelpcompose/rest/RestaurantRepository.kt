package com.example.restaurantsyelpcompose.rest

import com.example.restaurantsyelpcompose.ViewModel.Utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val serviceAPI: RestaurantAPI
): Repository {

    private val _restaurant = MutableStateFlow<UIState>(UIState.Empty)
    override val restaurant: StateFlow<UIState>
        get() = _restaurant

    /**
     *
     */
    override suspend fun getRestaurantList(latitude: Double, longitude: Double) {
        _restaurant.value = UIState.Loading

        try {
            val response = serviceAPI.getRestaurants(latitude, longitude)
            if (response.isSuccessful) {
                response.body()?.let {
                    _restaurant.value = UIState.Success(it.businesses ?: emptyList())
                } ?: throw Exception("Details response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            _restaurant.value = UIState.Failure(e.localizedMessage)
        }
    }

    /**
     *
     */
    override fun getRestaurantDetails(uid: String): Flow<UIState> = flow {
        emit(UIState.Loading)

        try {
            val response = serviceAPI.getRestaurantReviews(uid)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.Success(it.reviews ?: emptyList()))
                } ?: throw Exception("Details response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.Failure(e.localizedMessage))
        }
    }
}