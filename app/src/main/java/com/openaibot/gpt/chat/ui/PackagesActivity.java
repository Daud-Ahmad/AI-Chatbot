package com.openaibot.gpt.chat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.SharePreferences;
import com.openaibot.gpt.chat.databinding.ActivityPackagesBinding;
import com.openaibot.gpt.chat.utils.Constants;

public class PackagesActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler  {
    private BillingProcessor bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPackagesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_packages);

        bp = new BillingProcessor(this, Constants.LICENSE_KEY, this);
        bp.initialize();

        binding.lblCredit.setText("Get " + Constants.eight_coin +" search messages");
        binding.lblCredit1.setText("Get " + Constants.five_coin +" search messages");
        binding.lblCredit2.setText("Get " + Constants.one_coin +" search messages");

    }

    public void onClickPurchaseOne(View view){
        bp.purchase(this, Constants.ONE_DOLLAR_PRODUCT_ID);
    }

    public void onClickPurchaseFive(View view){
        bp.purchase(this, Constants.FIVE_DOLLAR_PRODUCT_ID);
    }

    public void onClickPurchaseEight(View view){
        bp.purchase(this, Constants.EIGHT_DOLLAR_PRODUCT_ID);
    }

    public void onBtnBack(View view){
        finish();
    }


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable PurchaseInfo details) {
        if(productId.equalsIgnoreCase(Constants.ONE_DOLLAR_PRODUCT_ID)){
            Constants.totalCoins = Constants.totalCoins + Constants.one_coin;
        }
        else if(productId.equalsIgnoreCase(Constants.FIVE_DOLLAR_PRODUCT_ID)){
            Constants.totalCoins = Constants.totalCoins + Constants.five_coin;
        }
        else if(productId.equalsIgnoreCase(Constants.EIGHT_DOLLAR_PRODUCT_ID)){
            Constants.totalCoins = Constants.totalCoins + Constants.eight_coin;
        }
        SharePreferences.saveString(this, Constants.COINS_KEY, String.valueOf(Constants.totalCoins));
        Toast.makeText(this, "Rewarded", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
//        Toast.makeText(this, "Error accrued. please try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    protected void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
}