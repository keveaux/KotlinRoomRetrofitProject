package com.i.kotlinroomretrofitproject

import android.app.Application
import androidx.room.Room
import com.i.kotlinroomretrofitproject.DB.CountryDatabase

class RoomViewModelApplication : Application() {

    companion object{
        var database : CountryDatabase ?= null

    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(applicationContext, CountryDatabase::class.java, "country_db").fallbackToDestructiveMigration().build()
    }
}