package com.openaibot.gpt.chat.client;


import com.openaibot.gpt.chat.models.ChatReq;
import com.openaibot.gpt.chat.models.ChatRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Endpoint {

    @POST("/v1/completions")
    Call<ChatRes> chat(@Header("Authorization") String token, @Body ChatReq chatReq);
}