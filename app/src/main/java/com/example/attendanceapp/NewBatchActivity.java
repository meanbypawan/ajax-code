package com.example.attendanceapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendanceapp.databinding.NewBatchBinding;
import com.example.attendanceapp.modal.Batch;
import com.example.attendanceapp.modal.Subject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewBatchActivity extends AppCompatActivity {
    NewBatchBinding binding;
    SharedPreferences sp;
    int fid;
    ArrayList<Subject>subjectList;
    ArrayAdapter<Subject>adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NewBatchBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        sp = getSharedPreferences("faculty",MODE_PRIVATE);
        fid = sp.getInt("id",0);
        getSubjectList();
        Calendar c = Calendar.getInstance();
        Date d = c.getTime();
        d.getHours();
        binding.tvBatchTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp = new TimePickerDialog(NewBatchActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       binding.tvBatchTime.setText(hourOfDay+":"+minute);
                    }
                },d.getHours(),d.getMinutes(),true);
                tp.show();
            }
        });
        binding.tvFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp = new TimePickerDialog(NewBatchActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.tvFromTime.setText(hourOfDay+":"+minute);
                    }
                },d.getHours(),d.getMinutes(),true);
                tp.show();
            }
        });
        binding.tvToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp = new TimePickerDialog(NewBatchActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.tvToTime.setText(hourOfDay+":"+minute);
                    }
                },d.getHours(),d.getMinutes(),true);
                tp.show();
            }
        });
        binding.tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long ctime = System.currentTimeMillis();
                SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
                String currentDate = sd.format(new Date(ctime));
                String arr[] = currentDate.split("/");
                DatePickerDialog dp = new DatePickerDialog(NewBatchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                      binding.tvStartDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },Integer.parseInt(arr[2]),Integer.parseInt(arr[1])-1,Integer.parseInt(arr[0]));
                dp.getDatePicker().setMinDate(ctime-1000);
                dp.show();

            }
        });
        binding.btnCreateBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Subject s = (Subject) binding.spinner2.getSelectedItem();
               String batchTime = binding.tvBatchTime.getText().toString();
               String fromTime = binding.tvFromTime.getText().toString();
               String toTime = binding.tvToTime.getText().toString();
               String startDate = binding.tvStartDate.getText().toString();
               Batch b = new Batch(batchTime,s.getSubjectTitle(),toTime,fromTime,startDate,"",fid,"Active");
               createBatch(b);
            }
        });
    }
    private void createBatch(Batch b){
        ProgressDialog pd = new ProgressDialog(NewBatchActivity.this);
        pd.setMessage("Please wait..");
        pd.show();
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        retrofit.create(BatchApis.class).createBatch(b).enqueue(new Callback<Batch>() {
            @Override
            public void onResponse(Call<Batch> call, Response<Batch> response) {
                pd.dismiss();
                Toast.makeText(NewBatchActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                if(response.code() == 200){
                   Batch b =  response.body();
                   Toast.makeText(NewBatchActivity.this, "Batch Created", Toast.LENGTH_SHORT).show();
                   finish();
                }
                else
                    Toast.makeText(NewBatchActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Batch> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(NewBatchActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getSubjectList(){
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        retrofit.create(SubjectApis.class).getSubjectList().enqueue(new Callback<ArrayList<Subject>>() {
            @Override
            public void onResponse(Call<ArrayList<Subject>> call, Response<ArrayList<Subject>> response) {
                if(response.code() == 200){
                    subjectList = response.body();
                    adapter = new ArrayAdapter<>(NewBatchActivity.this,R.layout.subject_item_list,R.id.tvSubject,subjectList);
                    binding.spinner2.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Subject>> call, Throwable t) {

            }
        });
    }
}
