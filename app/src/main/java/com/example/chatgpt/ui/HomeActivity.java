package com.example.chatgpt.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.bumptech.glide.Glide;
import com.example.chatgpt.Constants;
import com.example.chatgpt.R;
import com.example.chatgpt.databinding.ActivityHomeBinding;
import com.example.chatgpt.models.GenresModel;
import com.example.chatgpt.ui.adapters.GenresAdapter;
import com.example.chatgpt.ui.adapters.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        Glide.with(this)
                .load(R.drawable.gift)
                .into(binding.gift);

        binding.rvGenres.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<GenresModel> arrayList = new ArrayList<>();
        arrayList.add(new GenresModel(true, "Education"));
        arrayList.add(new GenresModel(false, "Entertain"));
        arrayList.add(new GenresModel(false, "Idea, Business"));
        arrayList.add(new GenresModel(false, "  Health  "));
        binding.rvGenres.setAdapter(new GenresAdapter(arrayList,this));

        binding.rvHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvHistory.setAdapter(new HistoryAdapter(arrayList, this));

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
//                    QueryProductDetailsParams queryProductDetailsParams =
//                            QueryProductDetailsParams.newBuilder()
//                                    .setProductList(
//                                            ImmutableList.of(
//                                                    QueryProductDetailsParams.Product.newBuilder()
//                                                            .setProductId("product_id_example")
//                                                            .setProductType(BillingClient.ProductType.INAPP)
//                                                            .build()))
//                                    .build();
//
//                    billingClient.queryProductDetailsAsync(
//                            queryProductDetailsParams,
//                            new ProductDetailsResponseListener() {
//                                public void onProductDetailsResponse(@NonNull BillingResult billingResult,
//                                                                     @NonNull List<ProductDetails> productDetailsList) {
//                                    // check billingResult
//                                    // process returned productDetailsList
//                                }
//                            }
//                    );
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String coins = Constants.totalCoins + getString(R.string.remaining_messages);
        binding.lblCoins.setText(coins);
    }

    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                    && purchases != null) {
                for (Purchase purchase : purchases) {
//                    handlePurchase(purchase);
                }
            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                // Handle an error caused by a user cancelling the purchase flow.
            } else {
                // Handle any other error codes.
            }
        }
    };

    private BillingClient billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build();

    public void onClickMessage(View view){
        startActivity(new Intent(this, ChatActivity.class));
    }
}