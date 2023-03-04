package com.openaibot.gpt.chat.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openaibot.gpt.chat.databinding.ItemHistoryBinding;
import com.openaibot.gpt.chat.models.HistoryModel;
import com.openaibot.gpt.chat.ui.ChatActivity;
import com.openaibot.gpt.chat.utils.Constants;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.DataViewHolder>
{
    Context context;
    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.bind(Constants.historyModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return Constants.historyModelArrayList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder
    {
        ItemHistoryBinding itemGenresBinding;
        public DataViewHolder(@NonNull ItemHistoryBinding itemGenresBinding) {
            super(itemGenresBinding.getRoot());
            this.itemGenresBinding = itemGenresBinding;
        }

        public void bind(HistoryModel item){
            itemGenresBinding.lblDate.setText(item.getDate());
            itemGenresBinding.lblQst.setText(item.getQuestion());
            itemGenresBinding.lblAns.setText(item.getAnswer());
            itemGenresBinding.colParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ChatActivity.class).putExtra("isHistory", true));
                }
            });
        }
    }
}