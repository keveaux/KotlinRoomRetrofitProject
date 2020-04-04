package com.i.kotlinroomretrofitproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("country_code")
    @Expose
    private String title;
    @SerializedName("number")
    @Expose
    private String body;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



//    @Override
//    public String toString() {
//        return "Post{" +
//                "title='" + title + '\'' +
//                ", body='" + body + '\'' +
//                '}';
//    }
}
