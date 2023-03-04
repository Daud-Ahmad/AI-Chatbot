package com.example.chatgpt.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.chatgpt.Constants;
import com.example.chatgpt.R;
import com.example.chatgpt.databinding.ActivityChatBinding;
import com.example.chatgpt.models.ChatScreenModel;
import com.example.chatgpt.ui.adapters.ChatAdapter;
import com.example.chatgpt.utils.AlertDialogueUtils;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private final ArrayList<ChatScreenModel> arrayList = new ArrayList<>();
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        Glide.with(this)
                .load(R.drawable.gift)
                .into(binding.gift);

        chatAdapter = new ChatAdapter(arrayList, this);
        binding.rvChat.setAdapter(chatAdapter);
        binding.rvChat.setLayoutManager(new LinearLayoutManager(this));
        scrollToEnd(arrayList.size() -1);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String coins = Constants.totalCoins + getString(R.string.remaining_messages);
        binding.lblCoins.setText(coins);
    }

    public void onClickCoins(View view){
        AlertDialogueUtils.showCoinsAlert(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                },new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }, this);
    }

    public void onBtnBack(View view){
        finish();
    }

    public void onClickChat(View view){

        String quest = binding.txtChat.getText().toString();
        if(quest.isEmpty()){
            Toast.makeText(this, "Please enter question first", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.txtChat.setText("");
        binding.btnSend.setEnabled(false);
        hideSoftKeyboard();

        ChatScreenModel model = new ChatScreenModel();
        model.setLabelText(quest);
        model.setYou(true);
        arrayList.add(model);

        ChatScreenModel model1 = new ChatScreenModel();
        model1.setLabelText("I'm fine. How can i help you.");
        model1.setLoading(true);
        arrayList.add(model1);
        chatAdapter.notifyDataSetChanged();
        scrollToEnd(arrayList.size() -1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.btnSend.setEnabled(true);
                arrayList.get(arrayList.size() -1).setLoading(false);
                arrayList.get(arrayList.size() -1).setLabelText("I'm fine. How can i help you.");
                chatAdapter.notifyItemChanged(arrayList.size() -1);
            }
        }, 2000);

//        ChatReq chatReq = new ChatReq();
////        String prompt = binding.lblChat.getText().toString();
//        chatReq.setPrompt("prompt");
//        Call<ChatRes> chatCall = API.chating().
//                chat(Constants.token, chatReq);
//        chatCall.enqueue(new Callback<ChatRes>() {
//            @Override
//            public void onResponse(@NotNull Call<ChatRes> call, @NotNull Response<ChatRes> response) {
//                if (response.isSuccessful()) {
//                    ChatRes chatRes = response.body();
////                    binding.lblResponse.setText(chatRes.getChoices().get(0).getText());
//                }
//                else {
//                    Toast.makeText(ChatActivity.this, response.message(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<ChatRes> call, @NotNull Throwable t) {
//                Toast.makeText(ChatActivity.this, "error", Toast.LENGTH_LONG).show();
//            }
//        });
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

    private void scrollToEnd(int position){
        binding.rvChat.scrollToPosition(position);
    }
}