package com.i.kotlinroomretrofitproject.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.i.kotlinroomretrofitproject.Model.CountryModel

@Database(entities = [(CountryModel::class)], version = 1)

abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao() : CountryDao
}