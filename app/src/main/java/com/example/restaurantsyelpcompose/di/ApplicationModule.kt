package com.example.restaurantsyelpcompose.di

import android.content.Context
import androidx.room.Room
import com.example.restaurantsyelpcompose.database.RestaurantDAO
import com.example.restaurantsyelpcompose.database.RestaurantsDatabase
import com.google.android.gms.location.FusedLocationProviderApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun providesFusedLocation(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun providesRestaurantsDatabase(
        @ApplicationContext context: Context
    ): RestaurantsDatabase =
        Room.databaseBuilder(
            context = context,
            klass = RestaurantsDatabase::class.java,
            name = "restaurants-db"
        ).build()

    @Provides
    fun providesDAO(
        restaurantsDatabase: RestaurantsDatabase
    ): RestaurantDAO =
        restaurantsDatabase.getRestaurantsDAO()
}