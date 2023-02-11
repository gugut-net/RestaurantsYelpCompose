package com.example.restaurantsyelpcompose.usecase.restaurants

import android.location.Location
import com.example.restaurantsyelpcompose.ViewModel.Utils.UIState
import com.example.restaurantsyelpcompose.database.RestaurantDAO
import com.example.restaurantsyelpcompose.model.BusinessDetail
import com.example.restaurantsyelpcompose.rest.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewRestaurantsUseCase @Inject constructor(

    private val repository: Repository,
    private val restaurantDAO: RestaurantDAO
) {

    fun getRestaurants(location: Location): Flow<UIState> = flow {
        repository.getRestaurantList(location.latitude, location.longitude)

        repository.restaurant.collect {

//            if (it is UIState.Success<*>) {
//                restaurantDAO.insertRestaurants((it.response as List<BusinessDetail>))
//            }
            emit(it)
        }
    }
}