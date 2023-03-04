package com.example.chatgpt.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatgpt.R;
import com.example.chatgpt.databinding.ItemGenresBinding;
import com.example.chatgpt.databinding.ItemHistoryBinding;
import com.example.chatgpt.models.GenresModel;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.DataViewHolder>
{
    ArrayList<GenresModel> genresModelArrayList;
    Context context;
    public HistoryAdapter(ArrayList<GenresModel> genresModelArrayList, Context context) {
        this.genresModelArrayList = genresModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class DataViewHolder extends RecyclerView.ViewHolder
    {
        ItemHistoryBinding itemGenresBinding;
        public DataViewHolder(@NonNull ItemHistoryBinding itemGenresBinding) {
            super(itemGenresBinding.getRoot());
            this.itemGenresBinding = itemGenresBinding;
        }

        public void bind(GenresModel item, int position){
        }
    }
}