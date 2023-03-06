package com.openaibot.gpt.chat.ui;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.SharePreferences;
import com.openaibot.gpt.chat.client.API;
import com.openaibot.gpt.chat.dao.BookmarkDatabase;
import com.openaibot.gpt.chat.databinding.ActivityChatBinding;
import com.openaibot.gpt.chat.models.ChatReq;
import com.openaibot.gpt.chat.models.ChatRes;
import com.openaibot.gpt.chat.models.ChatScreenModel;
import com.openaibot.gpt.chat.models.HistoryModel;
import com.openaibot.gpt.chat.ui.adapters.ChatAdapter;
import com.openaibot.gpt.chat.utils.Ads;
import com.openaibot.gpt.chat.utils.AlertDialogueUtils;
import com.openaibot.gpt.chat.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private final ArrayList<ChatScreenModel> arrayList = new ArrayList<>();
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        Ads.loadNativeAds(this, binding.nativeAdPlaceHolder);

        Glide.with(this)
                .load(R.drawable.gift)
                .into(binding.gift);

        boolean isHistory = getIntent().getBooleanExtra("isHistory", false);
        if(isHistory){
            for (int i = 0; i < Constants.historyModelArrayList.size(); i++){
                ChatScreenModel chatScreenModel = new ChatScreenModel();
                chatScreenModel.setYou(true);
                chatScreenModel.setLabelText(Constants.historyModelArrayList.get(i).getQuestion());
                arrayList.add(chatScreenModel);
                ChatScreenModel chatScreenModel1 = new ChatScreenModel();
                chatScreenModel1.setLabelText(Constants.historyModelArrayList.get(i).getAnswer());
                arrayList.add(chatScreenModel1);
            }
        }

        chatAdapter = new ChatAdapter(arrayList, this);
        binding.rvChat.setAdapter(chatAdapter);
        binding.rvChat.setLayoutManager(new LinearLayoutManager(this));
        scrollToEnd();
        if(arrayList.size() == 0){
            binding.colExample.setVisibility(View.VISIBLE);
        }
        else {
            binding.colExample.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String coins = Constants.totalCoins + " " + getString(R.string.remaining_messages);
        binding.lblCoins.setText(coins);
    }

    public void onClickCoins(View view){
        AlertDialogueUtils.onClickCoins(this, binding.lblCoins);
    }

    public void onBtnBack(View view){
        finish();
    }

    public void onClickChat(View view){

        if(!isOnline()){
            Toast.makeText(this, "No Internet connection.", Toast.LENGTH_SHORT).show();
            return;
        }

        String quest = binding.txtChat.getText().toString();
        if(quest.isEmpty()){
            Toast.makeText(this, "Please enter question first", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Constants.totalCoins <= 0){
            AlertDialogueUtils.onClickCoins(this, binding.lblCoins);
            return;
        }

        Constants.totalCoins = Constants.totalCoins - 1;
        String coins = Constants.totalCoins + " " + getString(R.string.remaining_messages);
        binding.lblCoins.setText(coins);
        SharePreferences.saveString(this, Constants.COINS_KEY, String.valueOf(Constants.totalCoins));

        binding.txtChat.setText("");
        binding.btnSend.setEnabled(false);
        hideSoftKeyboard();

        ChatScreenModel model = new ChatScreenModel();
        model.setLabelText(quest);
        model.setYou(true);
        arrayList.add(model);

        binding.colExample.setVisibility(View.GONE);

        ChatScreenModel model1 = new ChatScreenModel();
        model1.setLabelText("");
        model1.setLoading(true);
        arrayList.add(model1);
        chatAdapter.notifyDataSetChanged();
        scrollToEnd();

        ChatReq chatReq = new ChatReq();
        String prompt = quest;
        chatReq.setPrompt(prompt);
        Call<ChatRes> chatCall = API.chating().
                chat(Constants.token, chatReq);
        chatCall.enqueue(new Callback<ChatRes>() {
            @Override
            public void onResponse(@NotNull Call<ChatRes> call, @NotNull Response<ChatRes> response) {
                if (response.isSuccessful()) {
                    ChatRes chatRes = response.body();
                    String answer = chatRes.getChoices().get(0).getText();
                    binding.btnSend.setEnabled(true);
                    arrayList.get(arrayList.size() -1).setLoading(false);
                    arrayList.get(arrayList.size() -1).setLabelText(answer);
                    chatAdapter.notifyItemChanged(arrayList.size() -1);
                    HistoryModel historyModel = new HistoryModel();
                    historyModel.setQuestion(quest);
                    historyModel.setAnswer(answer);
                    double timeStamp = Calendar.getInstance().getTimeInMillis();
                    historyModel.setId(String.valueOf(timeStamp));
                    Date myDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
                    String date = dateFormat.format(myDate);
                    historyModel.setDate(date);
                    Constants.historyModelArrayList.add(historyModel);
                    BookmarkDatabase mDatabase = BookmarkDatabase.Companion.getDatabase(ChatActivity.this);
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                mDatabase.userDao().insertDua(historyModel);
                            } catch (Exception e) {
                            }
                        }
                    }.start();
                }
                else {
                    Toast.makeText(ChatActivity.this, "Sorry. Getting error. please try again.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ChatRes> call, @NotNull Throwable t) {
                Toast.makeText(ChatActivity.this, "Sorry. Getting error. please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    public void scrollToEnd(){
        binding.rvChat.scrollToPosition(arrayList.size() -1);
    }

    private boolean isOnline() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        if (manager != null) {
            if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true;
                    }
                }
            } else {

                try {
                    NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        return true;
                    }
                } catch (Exception e) {
                }
            }
        }
        return false;
    }
}