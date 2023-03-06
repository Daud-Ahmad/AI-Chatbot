package com.openaibot.gpt.chat.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.SharePreferences;
import com.openaibot.gpt.chat.ui.ChatActivity;

import androidx.annotation.NonNull;

public class Ads
{
    public static InterstitialAd mInterstitialAd;
    public static RewardedAd rewardedAd;
//    public static void loadBanner(FrameLayout frameLayout, Activity activity) {
//        AdView adView = new AdView(activity);
//        adView.setAdUnitId(activity.getString(R.string.));
//        frameLayout.addView(adView);
//        AdRequest adRequest =
//                new AdRequest.Builder()
//                        .build();
//        AdSize adSize = Ads.getAdSize(activity);
//        adView.setAdSize(adSize);
//        adView.loadAd(adRequest);
//    }
//
//    public static AdSize getAdSize(Activity context) {
//        Display display = context.getWindowManager().getDefaultDisplay();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        display.getMetrics(outMetrics);
//        float widthPixels = outMetrics.widthPixels;
//        float density = outMetrics.density;
//        int adWidth = (int) (widthPixels / density);
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
//    }

//    public static void loadNativeAds(Activity context){
//        VideoOptions videoOptions = new VideoOptions.Builder()
//                .setStartMuted(false)
//                .build();
//
//        NativeAdOptions adOptions = new NativeAdOptions.Builder()
//                .setVideoOptions(videoOptions)
//                .build();
//
//        new AdLoader.Builder(context, context.getString(R.string.native_ad_unit))
//                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                    @Override
//                    public void onNativeAdLoaded(NativeAd nativeAd) {
//                        NativeAdView adView = (NativeAdView) context.getLayoutInflater()
//                                .inflate(R.layout.ad_unified, null);
//                        populateNativeAdView(nativeAd, adView);
//                        nativeAdd = adView;
//                    }
//                })
//                .withNativeAdOptions(adOptions)
//                .build()
//                .loadAd(new AdRequest.Builder().build());
//    }
//
//    public static void loadNativeAdsSmall(Activity context){
//        VideoOptions videoOptions = new VideoOptions.Builder()
//                .setStartMuted(false)
//                .build();
//
//        NativeAdOptions adOptions = new NativeAdOptions.Builder()
//                .setVideoOptions(videoOptions)
//                .build();
//
//        new AdLoader.Builder(context, context.getString(R.string.native_ad_unit))
//                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                    @Override
//                    public void onNativeAdLoaded(NativeAd nativeAd) {
//                        NativeAdView adView = (NativeAdView) context.getLayoutInflater()
//                                .inflate(R.layout.ad_unified, null);
//                        populateNativeAdView(nativeAd, adView);
//                        nativeAdd = adView;
//                    }
//                })
//                .withNativeAdOptions(adOptions)
//                .build()
//                .loadAd(new AdRequest.Builder().build());
//    }

    public static void loadNativeAds(Activity context, FrameLayout nativeAdd){
        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(false)
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        new AdLoader.Builder(context, context.getString(R.string.native_advance))
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
                        NativeAdView adView = (NativeAdView) context.getLayoutInflater()
                                .inflate(R.layout.ad_unified_small, null);
                        populateNativeAdView(nativeAd, adView);
                        try {
                            nativeAdd.removeAllViews();
                            nativeAdd.addView(adView);
                            nativeAdd.setVisibility(View.VISIBLE);
                        }
                        catch (Exception e){}
                        try {
                            if(context instanceof ChatActivity){
                                ((ChatActivity) context).scrollToEnd();
                            }
                        }
                        catch (Exception e){}
                    }
                })
                .withNativeAdOptions(adOptions)
                .build()
                .loadAd(new AdRequest.Builder().build());
    }

    public static void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }
        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);
    }

    public static void loadInterstitialAd(Activity context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, context.getString(R.string.inter_ad), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }

    public static void loadRewardedAd(Activity context, TextView lblCoins) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, context.getString(R.string.reward_video_ad),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        rewardedAd = null;
                        AlertDialogueUtils.hideLoadingDialogue();
                        Toast.makeText(context, "Loading failed. Try again later", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        AlertDialogueUtils.hideLoadingDialogue();
                        rewardedAd = ad;
                        Ads.rewardedAd.show(context, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                try {
                                    Constants.totalCoins = Constants.totalCoins + Constants.rv_coin;
                                    SharePreferences.saveString(context, Constants.COINS_KEY, String.valueOf(Constants.totalCoins));
                                }
                                catch (Exception e){}
                                try {
                                    String value = Constants.totalCoins + " " + context.getString(R.string.remaining_messages);
                                    lblCoins.setText(value);
                                }
                                catch (Exception e){}
                                try {
                                    Toast.makeText(context, "Rewarded", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e){}
                            }
                        });
                    }
                });
    }
}
