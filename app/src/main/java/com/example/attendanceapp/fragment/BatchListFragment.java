package com.example.attendanceapp.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.attendanceapp.BatchApis;
import com.example.attendanceapp.HomeActivity;
import com.example.attendanceapp.RetrofitClient;
import com.example.attendanceapp.adapter.BatchAdapter;
import com.example.attendanceapp.databinding.BatchListBinding;
import com.example.attendanceapp.modal.Batch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class BatchListFragment extends Fragment {
    SharedPreferences sp;
    int fid;
    BatchAdapter adapter;
    BatchListBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        binding = BatchListBinding.inflate(LayoutInflater.from(getActivity()));
        sp = getActivity().getSharedPreferences("faculty",MODE_PRIVATE);
        fid = sp.getInt("id",0);
        getBatchList();

        return binding.getRoot();
    }
    private void getBatchList(){
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        retrofit.create(BatchApis.class).getBatchList(""+fid,"Active").enqueue(new Callback<ArrayList<Batch>>() {
            @Override
            public void onResponse(Call<ArrayList<Batch>> call, Response<ArrayList<Batch>> response) {
                if(response.code() == 200){
                    ArrayList<Batch>al = response.body();
                    if(al.size()!=0){
                        adapter = new BatchAdapter(getActivity(),al);
                        binding.rv.setAdapter(adapter);
                        binding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                    else
                        Toast.makeText(getActivity(), "No Batch Created", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Batch>> call, Throwable t) {

            }
        });
    }
}
