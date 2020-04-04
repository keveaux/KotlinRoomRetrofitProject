package com.i.kotlinroomretrofitproject.Retrofit

import com.i.kotlinroomretrofitproject.Model.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {
    @GET("all")
    fun getAllCountries() : Call<List<CountryModel>>
}