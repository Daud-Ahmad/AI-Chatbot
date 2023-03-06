package com.openaibot.gpt.chat.ui.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.openaibot.gpt.chat.databinding.ItemHistory1Binding;
import com.openaibot.gpt.chat.models.HistoryModel;
import com.openaibot.gpt.chat.ui.ChatActivity;
import com.openaibot.gpt.chat.utils.Ads;
import com.openaibot.gpt.chat.utils.Constants;

public class HistoryAdapter1 extends RecyclerView.Adapter<HistoryAdapter1.DataViewHolder>
{
    Activity context;
    public HistoryAdapter1(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(ItemHistory1Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.bind(Constants.historyModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return Constants.historyModelArrayList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder
    {
        ItemHistory1Binding itemGenresBinding;
        public DataViewHolder(@NonNull ItemHistory1Binding itemGenresBinding) {
            super(itemGenresBinding.getRoot());
            this.itemGenresBinding = itemGenresBinding;
        }

        public void bind(HistoryModel item, int position){
            itemGenresBinding.lblDate.setText(item.getDate());
            itemGenresBinding.lblQst.setText(item.getQuestion());
            itemGenresBinding.lblAns.setText(item.getAnswer());
            itemGenresBinding.colParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constants.interShowCounter++;
                    if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
                        Ads.mInterstitialAd.show(context);
                        Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Ads.loadInterstitialAd(context);
                                context.startActivity(new Intent(context, ChatActivity.class).putExtra("isHistory", true));
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                Ads.mInterstitialAd = null;
                            }
                        });

                    } else {
                        context.startActivity(new Intent(context, ChatActivity.class).putExtra("isHistory", true));
                    }
                }
            });

            itemGenresBinding.btnCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", Constants.historyModelArrayList.get(position).getAnswer());
                    clipboard.setPrimaryClip(clip);
                }
            });
        }
    }
}