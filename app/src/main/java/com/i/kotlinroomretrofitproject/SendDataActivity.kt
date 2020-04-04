package com.i.kotlinroomretrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_send_data.*
import android.text.TextUtils
import com.i.kotlinroomretrofitproject.Model.Post
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.gson.JsonElement


class SendDataActivity : AppCompatActivity() {

    var mResponseTv: TextView? = null
    var mAPIService: APIService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_data)

        mAPIService = ApiUtils.getAPIService()
        mResponseTv = findViewById(R.id.tv_response)

        btn_submit.setOnClickListener(View.OnClickListener {

            val title = et_title.getText().toString().trim()
            val body = et_body.getText().toString().trim()
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                sendPost(title, body)
            }
        })
    }

    fun sendPost(title: String, body: String) {

        val phoneNumber = PhoneNumber(title, body)
        val phone = Phone(phoneNumber)

        mAPIService?.regUser(phone)?.enqueue(object : Callback<JsonElement> {


            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Toast.makeText(applicationContext, "code " + response.code(), Toast.LENGTH_LONG)
                    .show()


                if (response.isSuccessful()) {
                    showResponse(response.body().toString())
                    Toast.makeText(
                        applicationContext,
                        "success" + response.body().toString(),
                        Toast.LENGTH_LONG
                    ).show()

                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Toast.makeText(applicationContext, "failed $t", Toast.LENGTH_LONG).show()

            }


        })
    }

    fun showResponse(response: String) {

        mResponseTv?.text = response
    }
}
