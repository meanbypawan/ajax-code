package com.example.attendanceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendanceapp.R;
import com.example.attendanceapp.databinding.BatchItemListBinding;
import com.example.attendanceapp.modal.Batch;

import java.util.ArrayList;

public class BatchAdapter extends RecyclerView.Adapter<BatchAdapter.BatchViewHolder> {
    private Context context;
    private ArrayList<Batch>al;
    OnRecyclerViewclick listener;
    public BatchAdapter(Context context, ArrayList<Batch>al){
        this.context = context;
        this.al = al;
    }
    @NonNull
    @Override
    public BatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BatchItemListBinding binding = BatchItemListBinding.inflate(LayoutInflater.from(context),parent,false);
        return new BatchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull  BatchAdapter.BatchViewHolder holder, int position) {
      Batch b = al.get(position);
      holder.binding.tvBatchTitle.setText(b.getBatchSubject());
      holder.binding.tvStartDate.setText(b.getStartDate());
      holder.binding.tvBatchTime.setText("From "+b.getFromTime()+" To "+b.getToTime());
      if(b.getBatchSubject().equalsIgnoreCase("Java"))
          holder.binding.ivLogo.setImageResource(R.drawable.java);
      else if(b.getBatchSubject().equalsIgnoreCase("Android"))
          holder.binding.ivLogo.setImageResource(R.drawable.android);
      else if(b.getBatchSubject().equalsIgnoreCase("c programming"))
          holder.binding.ivLogo.setImageResource(R.drawable.c);
      else if(b.getBatchSubject().equalsIgnoreCase("CPP"))
          holder.binding.ivLogo.setImageResource(R.drawable.cpp);
      else if(b.getBatchSubject().equalsIgnoreCase("Javascript"))
          holder.binding.ivLogo.setImageResource(R.drawable.js);
      else if(b.getBatchSubject().equalsIgnoreCase("Web-designing"))
          holder.binding.ivLogo.setImageResource(R.drawable.wd);
      else if(b.getBatchSubject().equalsIgnoreCase("Python"))
          holder.binding.ivLogo.setImageResource(R.drawable.python);
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class BatchViewHolder extends RecyclerView.ViewHolder{
      BatchItemListBinding binding;
      public BatchViewHolder(BatchItemListBinding binding){
          super(binding.getRoot());
          this.binding = binding;
          this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int position = getAdapterPosition();
                  if(position!=RecyclerView.NO_POSITION && listener!=null){
                      Batch b = al.get(position);
                      listener.onItemClick(b,position);
                  }
              }
          });
      }
  }
  public interface OnRecyclerViewclick{
        void onItemClick(Batch batch,int position);
  }
  public void setOnRecyclerViewClick(OnRecyclerViewclick listener){
        this.listener = listener;
  }
}
