package com.i.kotlinroomretrofitproject;

import com.google.gson.JsonElement;
import com.i.kotlinroomretrofitproject.Model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("/api/v1/phone_number_verifications")
    @FormUrlEncoded
    Call<Post> savePost(@Field("country_code") String title,
                        @Field("number") String body);

    //send phone number to get sms verification code
    @POST("/api/v1/phone_verification")
    Call<JsonElement> regUser(@Body Phone phone);

}
