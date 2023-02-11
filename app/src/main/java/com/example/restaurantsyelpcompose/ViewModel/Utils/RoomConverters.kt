package com.example.restaurantsyelpcompose.ViewModel.Utils

import androidx.room.TypeConverter
import com.example.restaurantsyelpcompose.model.Coordinates
import com.squareup.moshi.Moshi
import javax.inject.Inject

class RoomConverters @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromCategoriesList(categories: Coordinates?): String? {
        if (categories == null) {
            return null
        }

        return moshi.adapter(Coordinates::class.java).toJson(categories)
    }

    @TypeConverter
    fun toCategoryList(countryLangString: String?): Coordinates? {
        if (countryLangString == null) {
            return null
        }

        return moshi.adapter(Coordinates::class.java).fromJson(countryLangString)
    }
}