package com.example.chatgpt.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.chatgpt.Constants;
import com.example.chatgpt.R;
import com.example.chatgpt.client.API;
import com.example.chatgpt.databinding.ActivityChatBinding;
import com.example.chatgpt.models.ChatReq;
import com.example.chatgpt.models.ChatRes;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
    }

    public void onClickChat(View view){
        ChatReq chatReq = new ChatReq();
        String prompt = binding.lblChat.getText().toString();
        chatReq.setPrompt(prompt);
        Call<ChatRes> chatCall = API.chating().
                chat(Constants.token, chatReq);
        chatCall.enqueue(new Callback<ChatRes>() {
            @Override
            public void onResponse(@NotNull Call<ChatRes> call, @NotNull Response<ChatRes> response) {
                if (response.isSuccessful()) {
                    ChatRes chatRes = response.body();
                    binding.lblResponse.setText(chatRes.getChoices().get(0).getText());
                }
                else {
                    Toast.makeText(ChatActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ChatRes> call, @NotNull Throwable t) {
                Toast.makeText(ChatActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }
}