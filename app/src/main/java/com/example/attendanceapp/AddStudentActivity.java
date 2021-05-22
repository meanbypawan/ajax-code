package com.example.attendanceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.attendanceapp.adapter.BatchAdapter;
import com.example.attendanceapp.databinding.AddStudentBinding;
import com.example.attendanceapp.modal.Batch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddStudentActivity extends AppCompatActivity {
    AddStudentBinding binding;
    SharedPreferences sp;
    BatchAdapter adapter;
    int fid;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddStudentBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        sp = getSharedPreferences("faculty",MODE_PRIVATE);
        fid = sp.getInt("id",0);
        getBatchList();
    }
    private void getBatchList(){
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        retrofit.create(BatchApis.class).getBatchList(""+fid,"Active").enqueue(new Callback<ArrayList<Batch>>() {
            @Override
            public void onResponse(Call<ArrayList<Batch>> call, Response<ArrayList<Batch>> response) {
                if(response.code() == 200){
                    ArrayList<Batch>al = response.body();
                    if(al.size()!=0){
                        adapter = new BatchAdapter(AddStudentActivity.this,al);
                        binding.rv.setAdapter(adapter);
                        binding.rv.setLayoutManager(new LinearLayoutManager(AddStudentActivity.this));
                        adapter.setOnRecyclerViewClick(new BatchAdapter.OnRecyclerViewclick() {
                            @Override
                            public void onItemClick(Batch batch, int position) {
                                sendUserToAddNewStudentActivity(batch);
                            }
                        });
                    }
                    else
                        Toast.makeText(AddStudentActivity.this, "No Batch Created", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Batch>> call, Throwable t) {

            }
        });
    }
    private void sendUserToAddNewStudentActivity(Batch b){
        Intent in = new Intent(AddStudentActivity.this,AddNewStudentActivity.class);
        in.putExtra("batch",b);
        startActivity(in);
    }
}
