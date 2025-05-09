package com.openaibot.gpt.chat.utils;

import com.openaibot.gpt.chat.models.HistoryModel;

import java.util.ArrayList;

public class Constants {
    public static String token = "Bearer Your_API_TOKEN";

    public static final String ONE_DOLLAR_PRODUCT_ID = "dollar.1_product";


    public static final String LICENSE_KEY = "YOUR_LICENSE_KEY";
    public static final String FIVE_DOLLAR_PRODUCT_ID = "dollar.5_product";
    public static final String EIGHT_DOLLAR_PRODUCT_ID = "dollar.8_product";

    public static ArrayList<HistoryModel> historyModelArrayList = new ArrayList<>();

    public static final String COINS_KEY = "coins_keys";

    public static int totalCoins = 0;
    public static int rv_coin = 1;
    public static int one_coin = 40;
    public static int five_coin = 220;
    public static int eight_coin = 350;
    public static int counter = 2;
    public static int interShowCounter = 1;
}
