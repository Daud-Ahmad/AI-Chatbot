package com.openaibot.gpt.chat.models;

public class FirebaseDataModel {
    String token;
    String rv_coin;
    String one_coin;
    String five_coin;
    String eight_coin;
    String counter;

    public FirebaseDataModel() {
    }

    public FirebaseDataModel(String token, String rv_coin, String one_coin, String five_coin, String eight_coin, String counter) {
        this.token = token;
        this.rv_coin = rv_coin;
        this.one_coin = one_coin;
        this.five_coin = five_coin;
        this.eight_coin = eight_coin;
        this.counter = counter;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRv_coin() {
        return rv_coin;
    }

    public void setRv_coin(String rv_coin) {
        this.rv_coin = rv_coin;
    }

    public String getOne_coin() {
        return one_coin;
    }

    public void setOne_coin(String one_coin) {
        this.one_coin = one_coin;
    }

    public String getFive_coin() {
        return five_coin;
    }

    public void setFive_coin(String five_coin) {
        this.five_coin = five_coin;
    }

    public String getEight_coin() {
        return eight_coin;
    }

    public void setEight_coin(String eight_coin) {
        this.eight_coin = eight_coin;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }
}
