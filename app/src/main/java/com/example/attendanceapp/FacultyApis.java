package com.example.attendanceapp;

import com.example.attendanceapp.modal.Faculty;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FacultyApis {
  @POST("/faculty/save")
  public Call<Faculty> save(@Body Faculty f);

  @POST("/faculty/login")
  public Call<Faculty> login(@Body Faculty f);
}
