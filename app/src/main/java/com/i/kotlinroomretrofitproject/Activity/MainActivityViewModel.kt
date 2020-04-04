package com.i.kotlinroomretrofitproject.Activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.i.kotlinroomretrofitproject.Model.CountryModel

class MainActivityViewModel : ViewModel() {

    lateinit var mainActivityRepository: MainActivityRepository

    init {
        mainActivityRepository = MainActivityRepository()
    }

    fun getAllCountriesList() : LiveData<List<CountryModel>>
    {
        return mainActivityRepository.getCountries()
    }

    fun getCountriesFromApiStore()
    {
        mainActivityRepository.ApiCallAndPutInDB()
    }
}