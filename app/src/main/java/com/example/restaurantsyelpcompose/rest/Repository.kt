package com.example.restaurantsyelpcompose.rest

import com.example.restaurantsyelpcompose.ViewModel.Utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val restaurant: StateFlow<UIState>
    suspend fun getRestaurantList(latitude: Double, longitude: Double)
    fun getRestaurantDetails(uid:String):  Flow<UIState>
}






