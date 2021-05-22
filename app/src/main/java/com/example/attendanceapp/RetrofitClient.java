package com.example.attendanceapp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
   public static Retrofit retrofit;
   public static Retrofit getRetrofitInstance(){
       if(retrofit == null){
           OkHttpClient client = new OkHttpClient.Builder()
                   .connectTimeout(100, TimeUnit.SECONDS)
                   .readTimeout(100,TimeUnit.SECONDS).build();
           retrofit = new Retrofit.Builder()
                   .baseUrl("https://track-attendance1.herokuapp.com")
                   .addConverterFactory(GsonConverterFactory.create())
                   .client(client)
                   .build();
       }
       return retrofit;
   }
}
