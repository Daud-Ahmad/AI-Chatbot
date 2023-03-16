package com.openaibot.gpt.chat.models;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
@Keep
public class FirebaseDataModel {
    String token;
    String rv_coin;
    String one_coin;
    String five_coin;
    String eight_coin;
    String counter;
    String is_app_blocked;
    String app_link;
    String is_new_version;
    String new_version;

    public FirebaseDataModel() {
    }

    public FirebaseDataModel(String token, String rv_coin, String one_coin, String five_coin,
                             String eight_coin, String counter, String is_app_blocked,
                             String app_link, String is_new_version, String new_version) {
        this.token = token;
        this.rv_coin = rv_coin;
        this.one_coin = one_coin;
        this.five_coin = five_coin;
        this.eight_coin = eight_coin;
        this.counter = counter;
        this.is_app_blocked = is_app_blocked;
        this.app_link = app_link;
        this.is_new_version = is_new_version;
        this.new_version = new_version;
    }

    public String getIs_app_blocked() {
        return is_app_blocked;
    }

    public void setIs_app_blocked(String is_app_blocked) {
        this.is_app_blocked = is_app_blocked;
    }

    public String getApp_link() {
        return app_link;
    }

    public void setApp_link(String app_link) {
        this.app_link = app_link;
    }

    public String getIs_new_version() {
        return is_new_version;
    }

    public void setIs_new_version(String is_new_version) {
        this.is_new_version = is_new_version;
    }

    public String getNew_version() {
        return new_version;
    }

    public void setNew_version(String new_version) {
        this.new_version = new_version;
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
