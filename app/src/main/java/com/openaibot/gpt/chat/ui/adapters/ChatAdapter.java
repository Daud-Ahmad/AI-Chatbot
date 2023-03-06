package com.openaibot.gpt.chat.ui.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.databinding.ItemChatBinding;
import com.openaibot.gpt.chat.databinding.ItemHistoryBinding;
import com.openaibot.gpt.chat.models.ChatScreenModel;
import com.openaibot.gpt.chat.utils.Constants;

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
                itemGenresBinding.lolProfile.setBackground(AppCompatResources.getDrawable(context, R.drawable.rectangle_selected1));
                itemGenresBinding.lblProfileName.setText(context.getString(R.string.app_name));
                itemGenresBinding.imgProfile.setImageDrawable(AppCompatResources.getDrawable(context, R.mipmap.ic_launcher));
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
                itemGenresBinding.lolProfile.setBackground(AppCompatResources.getDrawable(context, R.drawable.rectangle_selected1));
                itemGenresBinding.lblProfileName.setText(context.getString(R.string.app_name));
                itemGenresBinding.imgProfile.setImageDrawable(AppCompatResources.getDrawable(context, R.mipmap.ic_launcher));
                itemGenresBinding.crdParent.setCardBackgroundColor(AppCompatResources.getColorStateList(context, R.color.light_primary));
            }

            itemGenresBinding.btnCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = arrayList.get(position).getLabelText();
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", value);
                    clipboard.setPrimaryClip(clip);
                }
            });

            itemGenresBinding.btnForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = arrayList.get(position).getLabelText();
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.TEXT", value);
                    context.startActivity(Intent.createChooser(intent, "Share Via"));
                }
            });
        }
    }
}