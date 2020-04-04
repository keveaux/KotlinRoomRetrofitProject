package com.i.kotlinroomretrofitproject.Activity

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.i.kotlinroomretrofitproject.Model.CountryModel
import com.i.kotlinroomretrofitproject.Retrofit.RestApi
import com.i.kotlinroomretrofitproject.RoomViewModelApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityRepository {

    val BASE_URL = "https://restcountries.eu/rest/v2/"
    val TAG = MainActivityRepository::class.java.simpleName

    fun getCountries(): LiveData<List<CountryModel>> {
        return RoomViewModelApplication.database!!.countryDao().getAllCountries()
    }

    fun ApiCallAndPutInDB() {
        val gson = Gson()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()

        val restApi = retrofit.create<RestApi>(RestApi::class.java)

        restApi.getAllCountries().enqueue(object : Callback<List<CountryModel>> {
            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                Log.e(TAG, "Something went wrong")
            }

            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                Log.e(TAG, response!!.body().toString())


                when (response.code()) {
                    200 -> {
                        Thread(Runnable {
//                            RoomViewModelApplication.database!!.countryDao().deleteAllCountries()
                            RoomViewModelApplication.database!!.countryDao().insertAllCountries(response.body()!!)

                        }).start()

                        MainActivity.Shimmer.shimmerFrameLayout?.visibility = View.GONE
                        MainActivity.Shimmer.shimmerFrameLayout?.stopShimmerAnimation()

                    }


                }


            }

        })

    }
}