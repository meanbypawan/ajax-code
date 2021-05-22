package com.example.attendanceapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendanceapp.databinding.RegisterBinding;
import com.example.attendanceapp.modal.Faculty;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {
    RegisterBinding binding;
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegisterBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        sp = getSharedPreferences("faculty",MODE_PRIVATE);
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etName.getText().toString();
                String mobile = binding.etMobile.getText().toString();
                String password = binding.etPassword.getText().toString();
                if(TextUtils.isEmpty(name)){
                    binding.etName.setError("Name required");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                   binding.etMobile.setError("Mobile required");
                   return;
                }
                else if(mobile.length()!=10){
                    binding.etMobile.setError("Mobile must have 10 digit");
                    return;
                }
                else{
                    try{
                        Long.parseLong(mobile);
                    }
                    catch (Exception e){
                        binding.etMobile.setError("Only digit allowed");
                        return;
                    }
                }
                if(TextUtils.isEmpty(password)){
                    binding.etPassword.setError("Password required");
                    return;
                }
                // Call api here
                Retrofit retrofit = RetrofitClient.getRetrofitInstance();
                FacultyApis apis = retrofit.create(FacultyApis.class);
                Faculty f = new Faculty(name,mobile,password);
                ProgressDialog pd = new ProgressDialog(SignupActivity.this);
                pd.setMessage("please wait while registering..");
                pd.show();
                apis.save(f).enqueue(new Callback<Faculty>() {
                    @Override
                    public void onResponse(Call<Faculty> call, Response<Faculty> response) {
                        pd.dismiss();
                        if(response.code() == 200){
                           Faculty f = response.body();
                           Toast.makeText(SignupActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                           saveDataIntoPreference(f);
                           sendUserToHomeActivity();
                        }
                        else
                            Toast.makeText(SignupActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Faculty> call, Throwable t) {
                      pd.dismiss();
                        Toast.makeText(SignupActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void sendUserToHomeActivity(){
        Intent in  = new Intent(SignupActivity.this,HomeActivity.class);
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
