package com.example.attendanceapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendanceapp.modal.Batch;

public class AddNewStudentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = getIntent();
        Batch b = (Batch) in.getSerializableExtra("batch");
    }
}
