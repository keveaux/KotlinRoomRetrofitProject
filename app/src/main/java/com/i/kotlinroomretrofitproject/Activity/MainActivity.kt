package com.i.kotlinroomretrofitproject.Activity

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.i.kotlinroomretrofitproject.Activity.MainActivity.Shimmer.shimmerFrameLayout
import com.i.kotlinroomretrofitproject.CountryRecyclerViewAdapter
import com.i.kotlinroomretrofitproject.Model.CountryModel
import com.i.kotlinroomretrofitproject.R

class MainActivity : AppCompatActivity() {

    lateinit var countryRecyclerView: RecyclerView
    lateinit var mainActivityViewModel: MainActivityViewModel

    object Shimmer {
        var shimmerFrameLayout : ShimmerFrameLayout? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryRecyclerView=findViewById(R.id.countryRecyclerView)
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        if (isNetworkConnected(this)){
            mainActivityViewModel.getCountriesFromApiStore()
        }
        else{
            Toast.makeText(this,"No internet found.",Toast.LENGTH_LONG).show()
        }

        mainActivityViewModel.getAllCountriesList().observe(this, Observer<List<CountryModel>>{
            countryList->
            Log.e(MainActivity::class.java.simpleName,countryList.toString())


            if(countryList.isEmpty()){
                shimmerFrameLayout?.startShimmerAnimation()
                shimmerFrameLayout?.duration=10
                shimmerFrameLayout?.repeatCount=0
            }else{
                shimmerFrameLayout?.stopShimmerAnimation()
                shimmerFrameLayout?.visibility = View.GONE
            }
            setUpCountryRecyclerView(countryList!!)
        })

    }

    fun isNetworkConnected(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val  activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun setUpCountryRecyclerView(countries : List<CountryModel>){
        val countryRecyclerViewAdapter = CountryRecyclerViewAdapter(this,countries)

        Toast.makeText(applicationContext,""+countries.size,Toast.LENGTH_LONG).show()

        countryRecyclerView.adapter = countryRecyclerViewAdapter
        countryRecyclerView.layoutManager = GridLayoutManager(this,2)
        countryRecyclerView.setHasFixedSize(true)
    }


    override fun onPause() {
        super.onPause()
        shimmerFrameLayout?.stopShimmerAnimation()
    }
}
