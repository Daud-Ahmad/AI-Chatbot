package com.openaibot.gpt.chat.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.databinding.ItemGenresBinding;
import com.openaibot.gpt.chat.models.GenresModel;

import java.util.ArrayList;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.DataViewHolder>
{
    private final ArrayList<GenresModel> genresModelArrayList;
    private final Context context;
    private final CallBack callBack;



    public interface CallBack{
        void onCallBack(int position);
    }
    public GenresAdapter(ArrayList<GenresModel> genresModelArrayList, Context context, CallBack callBack) {
        this.genresModelArrayList = genresModelArrayList;
        this.context = context;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(ItemGenresBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.bind(genresModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return genresModelArrayList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder
    {
        ItemGenresBinding itemGenresBinding;
        public DataViewHolder(@NonNull ItemGenresBinding itemGenresBinding) {
            super(itemGenresBinding.getRoot());
            this.itemGenresBinding = itemGenresBinding;
        }

        public void bind(GenresModel item, int position){
            itemGenresBinding.lblTitle.setText(item.getLabelText());
            if(item.isItemSelected()){
                itemGenresBinding.colParent.setBackground(AppCompatResources.getDrawable(context, R.drawable.rectangle_selected));
                itemGenresBinding.lblTitle.setTextColor(AppCompatResources.getColorStateList(context, R.color.white));
            }
            else {
                itemGenresBinding.colParent.setBackground(AppCompatResources.getDrawable(context, R.drawable.rectangle));
                itemGenresBinding.lblTitle.setTextColor(AppCompatResources.getColorStateList(context, R.color.black));
            }

            itemGenresBinding.colParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onCallBack(position);
                    for (int i = 0; i < genresModelArrayList.size(); i++){
                        genresModelArrayList.get(i).setItemSelected(i == position);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}