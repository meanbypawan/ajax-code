package com.example.attendanceapp;

import com.example.attendanceapp.modal.Subject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SubjectApis {
  @GET("/subject/list")
  public Call<ArrayList<Subject>> getSubjectList();
}
