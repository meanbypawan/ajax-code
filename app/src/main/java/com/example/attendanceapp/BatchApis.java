package com.example.attendanceapp;

import com.example.attendanceapp.modal.Batch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BatchApis {
  @POST("/batch/create")
  public Call<Batch> createBatch(@Body Batch b);

  @GET("/batch/{fid}/{status}")
  public Call<ArrayList<Batch>> getBatchList(@Path("fid") String fid,@Path("status") String status);
}
