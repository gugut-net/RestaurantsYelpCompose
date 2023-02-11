package com.example.restaurantsyelpcompose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurantsyelpcompose.model.BusinessDetail

/**
 * Database events happening here
 */
@Database(entities = [
    BusinessDetail::class
],

    version = 1
)
abstract class RestaurantsDatabase : RoomDatabase() {
    abstract fun getRestaurantsDAO(): RestaurantDAO

}


