package com.example.attendanceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.attendanceapp.adapter.BatchAdapter;
import com.example.attendanceapp.databinding.HomeBinding;
import com.example.attendanceapp.fragment.BatchListFragment;
import com.example.attendanceapp.modal.Batch;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {
    HomeBinding binding;
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       binding = HomeBinding.inflate(LayoutInflater.from(this));
       setContentView(binding.getRoot());
       setSupportActionBar(binding.homeToolBar);
       binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Batch list"));
       binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Mark attendance"));
       sp =getSharedPreferences("faculty",MODE_PRIVATE);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll,new BatchListFragment());
        transaction.commit();
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               String title = tab.getText().toString();
               if(title.equalsIgnoreCase("Batch list")){
                   FragmentManager manager = getSupportFragmentManager();
                   FragmentTransaction transaction = manager.beginTransaction();
                   transaction.replace(R.id.ll,new BatchListFragment());
                   transaction.commit();
               }
               else if(title.equalsIgnoreCase("Mark attendance")){

               }
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("New Batch");
        menu.add("Add student");
        menu.add("Logout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        if(title.equals("Logout")){
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
            sendUserToLoginActivity();
        }
        else if(title.equals("New Batch")){
            sendUserToNewBatchActivity();
        }
        else if(title.equalsIgnoreCase("Add student")){
            sendUserToAddStudentActivity();
        }
        return super.onOptionsItemSelected(item);
    }
    private void sendUserToNewBatchActivity(){
        Intent in = new Intent(HomeActivity.this,NewBatchActivity.class);
        startActivity(in);
    }
    private void sendUserToAddStudentActivity(){
        Intent in = new Intent(HomeActivity.this,AddStudentActivity.class);
        startActivity(in);
    }
    private void sendUserToLoginActivity(){
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
        finish();
    }
}
