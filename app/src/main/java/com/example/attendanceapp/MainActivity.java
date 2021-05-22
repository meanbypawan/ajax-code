package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.attendanceapp.databinding.ActivityMainBinding;
import com.example.attendanceapp.modal.Faculty;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        sp = getSharedPreferences("faculty",MODE_PRIVATE);
        isUserLoggedIn();
        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facultyLogin();
            }
        });
        binding.tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendUserToSignUpActivity();
            }
        });
    }
    private void isUserLoggedIn(){
        SharedPreferences sp = getSharedPreferences("faculty",MODE_PRIVATE);
        int id = sp.getInt("id",0);
        if(id!=0){
            sendUserHomeActivity();
        }
    }
    private void facultyLogin(){
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        FacultyApis apis = retrofit.create(FacultyApis.class);
        String mobile = binding.etMobile.getText().toString();
        String password = binding.etPassword.getText().toString();
        if(TextUtils.isEmpty(mobile)){
            binding.etMobile.setError("Mobile required");
            return;
        }
        if(TextUtils.isEmpty(password)){
            binding.etPassword.setError("Password required");
            return;
        }
        Faculty f = new Faculty();
        f.setMobile(mobile);
        f.setPassword(password);
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("please wait..");
        pd.show();
        apis.login(f).enqueue(new Callback<Faculty>() {
            @Override
            public void onResponse(Call<Faculty> call, Response<Faculty> response) {
                pd.dismiss();
                if(response.code() == 200){
                  Faculty f = response.body();
                  saveDataIntoPreference(f);
                  sendUserHomeActivity();
                }
                else if(response.code()==404){
                    Toast.makeText(MainActivity.this, "Invalid mobile and password", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Faculty> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(MainActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendUserHomeActivity(){
        Intent in = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(in);
        finish();
    }
    private void sendUserToSignUpActivity(){
        Intent in = new Intent(MainActivity.this,SignupActivity.class);
        startActivity(in);
        finish();
    }
    private void saveDataIntoPreference(Faculty f){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("id",f.getId());
        editor.putString("name",f.getName());
        editor.putString("mobile",f.getMobile());
        editor.commit();

    }
}