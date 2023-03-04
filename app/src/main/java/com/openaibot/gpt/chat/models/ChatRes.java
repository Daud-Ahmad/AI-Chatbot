package com.openaibot.gpt.chat.models;

import java.util.ArrayList;
import java.util.List;

public class ChatRes {
    String id;
    List<Text> choices = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Text> getChoices() {
        return choices;
    }

    public void setChoices(List<Text> choices) {
        this.choices = choices;
    }
}
