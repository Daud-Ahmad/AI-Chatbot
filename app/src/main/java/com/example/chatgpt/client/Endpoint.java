package com.example.chatgpt.client;


import com.example.chatgpt.models.ChatReq;
import com.example.chatgpt.models.ChatRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Endpoint {

    @POST("/v1/completions")
    Call<ChatRes> chat(@Header("Authorization") String token, @Body ChatReq chatReq);
}