package com.example.chatgpt.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatgpt.R;
import com.example.chatgpt.databinding.ItemChatBinding;
import com.example.chatgpt.databinding.ItemHistoryBinding;
import com.example.chatgpt.models.ChatScreenModel;
import com.example.chatgpt.models.GenresModel;
import com.example.chatgpt.models.HistoryModel;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.DataViewHolder>
{
    ArrayList<ChatScreenModel> arrayList;
    Context context;
    public ChatAdapter(ArrayList<ChatScreenModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(ItemChatBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.bind(arrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder
    {
        ItemChatBinding itemGenresBinding;
        public DataViewHolder(@NonNull ItemChatBinding itemGenresBinding) {
            super(itemGenresBinding.getRoot());
            this.itemGenresBinding = itemGenresBinding;
        }

        public void bind(ChatScreenModel item, int position){
            itemGenresBinding.lblMessage.setText(item.getLabelText());
            if(item.isLoading()){
                Glide.with(context)
                        .load(R.drawable.loading)
                        .into(itemGenresBinding.imgLoading);
                itemGenresBinding.imgLoading.setVisibility(View.VISIBLE);
                itemGenresBinding.lblMessage.setVisibility(View.GONE);
                itemGenresBinding.btnCopy.setVisibility(View.INVISIBLE);
                itemGenresBinding.btnForward.setVisibility(View.INVISIBLE);
                itemGenresBinding.lolProfile.setBackground(AppCompatResources.getDrawable(context, R.drawable.rectangle_selected));
                itemGenresBinding.lblProfileName.setText(context.getString(R.string.app_name));
                itemGenresBinding.imgProfile.setImageDrawable(AppCompatResources.getDrawable(context, R.mipmap.ic_launcher_foreground));
                itemGenresBinding.crdParent.setCardBackgroundColor(AppCompatResources.getColorStateList(context, R.color.light_primary));
            }
            else if(item.isYou()){
                itemGenresBinding.imgLoading.setVisibility(View.GONE);
                itemGenresBinding.lblMessage.setVisibility(View.VISIBLE);
                itemGenresBinding.btnCopy.setVisibility(View.INVISIBLE);
                itemGenresBinding.btnForward.setVisibility(View.INVISIBLE);
                itemGenresBinding.lolProfile.setBackground(AppCompatResources.getDrawable(context, R.drawable.rectangle_unselected));
                itemGenresBinding.lblProfileName.setText("You");
                itemGenresBinding.imgProfile.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_person));
                itemGenresBinding.crdParent.setCardBackgroundColor(AppCompatResources.getColorStateList(context, R.color.gray));
            }
            else {
                itemGenresBinding.imgLoading.setVisibility(View.GONE);
                itemGenresBinding.lblMessage.setVisibility(View.VISIBLE);
                itemGenresBinding.btnCopy.setVisibility(View.VISIBLE);
                itemGenresBinding.btnForward.setVisibility(View.VISIBLE);
                itemGenresBinding.lolProfile.setBackground(AppCompatResources.getDrawable(context, R.drawable.rectangle_selected));
                itemGenresBinding.lblProfileName.setText(context.getString(R.string.app_name));
                itemGenresBinding.imgProfile.setImageDrawable(AppCompatResources.getDrawable(context, R.mipmap.ic_launcher_foreground));
                itemGenresBinding.crdParent.setCardBackgroundColor(AppCompatResources.getColorStateList(context, R.color.light_primary));
            }
        }
    }
}