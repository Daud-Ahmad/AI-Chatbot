package com.example.chatgpt.models;

public class ChatReq {
    String model = "text-davinci-003";
    int temperature = 0;
    int max_tokens = 1000;
    String prompt;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
