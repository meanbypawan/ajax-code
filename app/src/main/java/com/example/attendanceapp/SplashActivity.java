package com.example.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendanceapp.databinding.SplashBinding;

public class SplashActivity extends AppCompatActivity {
    SplashBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                 sendUserToLoginActivity();
            }
        },5000);
    }
    private void sendUserToLoginActivity(){
        Intent in = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(in);
        finish();
    }
}
