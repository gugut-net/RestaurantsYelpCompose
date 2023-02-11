package com.example.restaurantsyelpcompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantsyelpcompose.model.BusinessDetail

@Dao
interface RestaurantDAO {

    @Query("SELECT * FROM restaurants_table")
    suspend fun getAllRestaurants(): List<BusinessDetail>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRestaurants(restaurants: BusinessDetail)

    @Query("SELECT * FROM restaurants_table WHERE is LIKE :restId LIMIT 1")
    suspend fun getRestaurantsById(restId: String): BusinessDetail



}