package com.palmdev.expressenglish

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.palmdev.expressenglish.data.User

object Ads {
    private var mRewardedAd: RewardedAd? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var TAG = "ADS"

    fun loadRewardedAd(context: Context) {

        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(context, context.getString(R.string.AD_REWARDED_ID), adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.message)
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mRewardedAd = rewardedAd
                }
            }
        )
    }

    fun showRewardedVideo(
        context: Context,
        activity: Activity,
        listener: OnUserEarnedRewardListener
    ) {

        mRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad was shown.")
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                // Called when ad fails to show.
                Log.d(TAG, "Ad failed to show.")
                mRewardedAd = null
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad was dismissed.")
                mRewardedAd = null
                loadRewardedAd(context)
            }
        }

        mRewardedAd?.show(activity, listener)
    }

    fun loadInterstitialAd(context: Context) {

        if (User.getPremiumStatus(context)) return

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            context,
            context.getString(R.string.AD_INTERSTITIAL_ID),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    super.onAdFailedToLoad(adError)
                    Log.d(TAG, adError.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            }
        )

    }

    fun showInterstitialAd(context: Context, activity: Activity) {

        if (User.getPremiumStatus(context)) return

        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                    mInterstitialAd = null
                    loadInterstitialAd(context)
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    Log.d(TAG, "Ad failed to show.")
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    // Called when ad is dismissed.
                }
            }
            mInterstitialAd?.show(activity)
        }
    }

    private fun populateNativeAd(nativeAd: NativeAd, adView: NativeAdView) {
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.priceView = adView.findViewById(R.id.ad_price)
        adView.starRatingView = adView.findViewById(R.id.ad_stars)
        adView.storeView = adView.findViewById(R.id.ad_store)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)

        val headlineLoading = adView.findViewById<View>(R.id.ad_headline_loading)

        (adView.headlineView as TextView).text = nativeAd.headline
        headlineLoading.visibility = View.INVISIBLE

        if (nativeAd.icon == null) {
            adView.iconView?.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(nativeAd.icon?.drawable)
            (adView.iconView as ImageView).setBackgroundColor(Color.TRANSPARENT)
        }

        if (nativeAd.callToAction == null) {
            adView.callToActionView?.visibility = View.INVISIBLE
        } else {
            adView.callToActionView?.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }

        if (nativeAd.price == null) {
            adView.priceView?.visibility = View.INVISIBLE
        } else {
            adView.priceView?.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }

        if (nativeAd.store == null) {
            adView.storeView?.visibility = View.INVISIBLE
        } else {
            adView.storeView?.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }

        if (nativeAd.starRating == null) {
            adView.starRatingView?.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            adView.starRatingView?.visibility = View.VISIBLE
        }


        adView.setNativeAd(nativeAd)
    }

    fun loadNativeAd(context: Context,  root: ViewGroup): NativeAd? {
        var nativeAd: NativeAd? = null
        val builder = AdLoader.Builder(context, context.getString(R.string.AD_NATIVE_ID))

        builder.forNativeAd { ad: NativeAd ->
            // You must call destroy on old ads when you are done with them,
            // otherwise you will have a memory leak.
            nativeAd?.destroy()
            nativeAd = ad

            val adView = LayoutInflater.from(context)
                .inflate(R.layout.item_native_ad, null) as NativeAdView
            populateNativeAd(nativeAd = ad, adView = adView)
            root.removeAllViews()
            root.addView(adView)

        }

        val videoOptions = VideoOptions.Builder()
            .setStartMuted(false)
            .build()

        val adOptions = NativeAdOptions.Builder()
            .setVideoOptions(videoOptions)
            .build()

        builder.withNativeAdOptions(adOptions)

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Toast.makeText(
                    context, "Failed to load native ad with error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

        return nativeAd
    }
}


