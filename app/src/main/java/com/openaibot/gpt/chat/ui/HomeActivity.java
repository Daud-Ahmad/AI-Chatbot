package com.openaibot.gpt.chat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.openaibot.gpt.chat.utils.Constants;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.databinding.ActivityHomeBinding;
import com.openaibot.gpt.chat.models.GenresModel;
import com.openaibot.gpt.chat.ui.adapters.GenresAdapter;
import com.openaibot.gpt.chat.ui.adapters.HistoryAdapter;
import com.openaibot.gpt.chat.utils.AlertDialogueUtils;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements GenresAdapter.CallBack {

    private ActivityHomeBinding binding;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        Glide.with(this)
                .load(R.drawable.gift)
                .into(binding.gift);

        binding.rvHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        historyAdapter = new HistoryAdapter(this);
        binding.rvHistory.setAdapter(historyAdapter);

        if(Constants.historyModelArrayList.size() == 0){
            binding.lblHistory.setVisibility(View.GONE);
            binding.lblMore.setVisibility(View.GONE);
            binding.rvHistory.setVisibility(View.GONE);
        }

        binding.rvGenres.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<GenresModel> arrayList = new ArrayList<>();
        arrayList.add(new GenresModel(true, "Education"));
        arrayList.add(new GenresModel(false, "Entertain"));
        arrayList.add(new GenresModel(false, "Idea, Business"));
        arrayList.add(new GenresModel(false, "  Health  "));
        binding.rvGenres.setAdapter(new GenresAdapter(arrayList,this, this));

        initTaskViewData(0);

//        billingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
//                if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
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
//                }
//            }
//            @Override
//            public void onBillingServiceDisconnected() {
//                // Try to restart the connection on the next request to
//                // Google Play by calling the startConnection() method.
//            }
//        });
    }


    public void onClickMore(View view){
        startActivity(new Intent(this, HistoryActivity.class));
    }


    public void onCrdChoose1(View view){
        startActivity(new Intent(this, ChatActivity.class)
                .putExtra("title", binding.lblTitle1.getText().toString())
                .putExtra("desc", binding.lblMsg1.getText().toString()));
    }

    public void onCrdChoose2(View view){
        startActivity(new Intent(this, ChatActivity.class)
                .putExtra("title", binding.lblTitle2.getText().toString())
                .putExtra("desc", binding.lblMsg2.getText().toString()));
    }

    public void onCrdChoose3(View view){
        startActivity(new Intent(this, ChatActivity.class)
                .putExtra("title", binding.lblTitle3.getText().toString())
                .putExtra("desc", binding.lblMsg3.getText().toString()));
    }

    public void onCrdChoose4(View view){
        startActivity(new Intent(this, ChatActivity.class)
                .putExtra("title", binding.lblTitle4.getText().toString())
                .putExtra("desc", binding.lblMsg4.getText().toString()));
    }

    public void onCrdChoose5(View view){
        startActivity(new Intent(this, ChatActivity.class)
                .putExtra("title", binding.lblTitle5.getText().toString())
                .putExtra("desc", binding.lblMsg5.getText().toString()));
    }

    public void onClickCoins(View view){
        AlertDialogueUtils.showCoinsAlert(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                },new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }, this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String coins = Constants.totalCoins + " " + getString(R.string.remaining_messages);
        binding.lblCoins.setText(coins);
        if(Constants.historyModelArrayList.size() == 0){
            binding.lblHistory.setVisibility(View.GONE);
            binding.lblMore.setVisibility(View.GONE);
            binding.rvHistory.setVisibility(View.GONE);
        }
        else {
            binding.lblHistory.setVisibility(View.VISIBLE);
            binding.lblMore.setVisibility(View.VISIBLE);
            binding.rvHistory.setVisibility(View.VISIBLE);
            try {
                historyAdapter.notifyDataSetChanged();
            }
            catch (Exception e){}
        }
    }

//    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
//        @Override
//        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
//            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
//                    && purchases != null) {
//                for (Purchase purchase : purchases) {
////                    handlePurchase(purchase);
//                }
//            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
//                // Handle an error caused by a user cancelling the purchase flow.
//            } else {
//                // Handle any other error codes.
//            }
//        }
//    };

//    private BillingClient billingClient = BillingClient.newBuilder(this)
//            .setListener(purchasesUpdatedListener)
//            .enablePendingPurchases()
//            .build();

    public void onClickMessage(View view){
        startActivity(new Intent(this, ChatActivity.class));
    }

    private void initTaskViewData(int index){
        switch (index){
            case 0:
                binding.lblTitle1.setText(getString(R.string.edu_title_1));
                binding.lblMsg1.setText(getString(R.string.edu_desc_1));

                binding.lblTitle2.setText(getString(R.string.edu_title_2));
                binding.lblMsg2.setText(getString(R.string.edu_desc_2));

                binding.lblTitle3.setText(getString(R.string.edu_title_3));
                binding.lblMsg3.setText(getString(R.string.edu_desc_3));

                binding.lblTitle4.setText(getString(R.string.edu_title_4));
                binding.lblMsg4.setText(getString(R.string.edu_desc_4));

                binding.lblTitle5.setText(getString(R.string.edu_title_5));
                binding.lblMsg5.setText(getString(R.string.edu_desc_5));
                break;

            case 1:
                binding.lblTitle1.setText(getString(R.string.ent_title_1));
                binding.lblMsg1.setText(getString(R.string.ent_desc_1));

                binding.lblTitle2.setText(getString(R.string.ent_title_2));
                binding.lblMsg2.setText(getString(R.string.ent_desc_2));

                binding.lblTitle3.setText(getString(R.string.ent_title_3));
                binding.lblMsg3.setText(getString(R.string.ent_desc_3));

                binding.lblTitle4.setText(getString(R.string.ent_title_4));
                binding.lblMsg4.setText(getString(R.string.ent_desc_4));

                binding.lblTitle5.setText(getString(R.string.ent_title_5));
                binding.lblMsg5.setText(getString(R.string.ent_desc_5));
                break;

            case 2:
                binding.lblTitle1.setText(getString(R.string.idea_title_1));
                binding.lblMsg1.setText(getString(R.string.idea_desc_1));

                binding.lblTitle2.setText(getString(R.string.idea_title_2));
                binding.lblMsg2.setText(getString(R.string.idea_desc_2));

                binding.lblTitle3.setText(getString(R.string.idea_title_3));
                binding.lblMsg3.setText(getString(R.string.idea_desc_3));


                binding.lblTitle4.setText(getString(R.string.idea_title_4));
                binding.lblMsg4.setText(getString(R.string.idea_desc_4));

                binding.lblTitle5.setText(getString(R.string.idea_title_5));
                binding.lblMsg5.setText(getString(R.string.idea_desc_5));
                break;

            case 3:
                binding.lblTitle1.setText(getString(R.string.health_title_1));
                binding.lblMsg1.setText(getString(R.string.health_desc_1));

                binding.lblTitle2.setText(getString(R.string.health_title_2));
                binding.lblMsg2.setText(getString(R.string.health_desc_2));

                binding.lblTitle3.setText(getString(R.string.health_title_3));
                binding.lblMsg3.setText(getString(R.string.health_desc_3));

                binding.lblTitle4.setText(getString(R.string.health_title_4));
                binding.lblMsg4.setText(getString(R.string.health_desc_4));

                binding.lblTitle5.setText(getString(R.string.health_title_5));
                binding.lblMsg5.setText(getString(R.string.health_desc_5));

        }
    }

    @Override
    public void onCallBack(int position) {
        initTaskViewData(position);
    }
}