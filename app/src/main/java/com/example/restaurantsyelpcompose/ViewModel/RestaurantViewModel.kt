package com.example.restaurantsyelpcompose.ViewModel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsyelpcompose.ViewModel.Utils.UIState
import com.example.restaurantsyelpcompose.location.LocationUseCase
import com.example.restaurantsyelpcompose.model.BusinessDetail
import com.example.restaurantsyelpcompose.usecase.restaurants.NewRestaurantsUseCase
import com.example.restaurantsyelpcompose.usecase.restaurants.ReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantsUseCase: NewRestaurantsUseCase,
    private val reviewsUseCase: ReviewsUseCase,
    private val locationUseCase: LocationUseCase
): ViewModel() {

    var permissionsGranted: Boolean = false

    /**
     * this is our RestaurantViewModel add
     * variables add livedata mutable live data add livedata of ui state methods
     * transcendentalist & get restaurant details that receives UID string
     */

    private val _data = MutableLiveData<UIState>()
    val data: LiveData<UIState>
        get() = _data

    var selectedRest: BusinessDetail? = null
    private var mLocation: Location? = null

    fun retrieveLocation() {
        if (permissionsGranted) {
            viewModelScope.launch {
                locationUseCase.location.collect {
                    mLocation = it
                }
            }

            locationUseCase.getLastLocation()
        }
    }


    fun getRestaurantList() {
        retrieveLocation()

        mLocation?.let {
            viewModelScope.launch {
                restaurantsUseCase.getRestaurants(it).collect {
                    _data.postValue(it)
                }
            }
        }
    }

    fun getReviews() {
        selectedRest?.let {
            it.id?.let { restId ->
                viewModelScope.launch {
                    reviewsUseCase.getReviews(restId).collect {

                    }
                }
            }
        }
    }
}