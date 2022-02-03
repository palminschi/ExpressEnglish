package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.SkuType
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.Security
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.FragmentShopBinding
import java.io.IOException
import java.util.*


class ShopFragment : Fragment(R.layout.fragment_shop), PurchasesUpdatedListener {

    private lateinit var binding: FragmentShopBinding
    private var mPrice = ""
    private val mHandler = Handler(Looper.getMainLooper())
    private lateinit var mCallback: OnBackPressedCallback
    private var billingClient: BillingClient? = null
    private var mPurchaseStatus = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShopBinding.bind(view)

        binding.tvOldPrice.text = Html.fromHtml(getString(R.string.oldPrice))
        mPrice = getString(R.string.premium_account_price)
        binding.tvNewPrice.text = mPrice

        // Init Countdown Timer
        mHandler.post(object : Runnable {
            override fun run() {
                mHandler.postDelayed(this, 1000)
                updateTimer()
            }
        })

        // Purchase
        billingClient = BillingClient.newBuilder(requireContext())
            .enablePendingPurchases().setListener(this).build()
        billingClient!!.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    val queryPurchase = billingClient!!.queryPurchases(SkuType.INAPP)
                    val queryPurchases = queryPurchase.purchasesList
                    if (queryPurchases != null && queryPurchases.size > 0) {
                        handlePurchases(queryPurchases)
                    } else {
                        User.setPremiumStatus(false)
                    }
                }
            }

            override fun onBillingServiceDisconnected() {}
        })


        mPurchaseStatus = User.getPremiumStatus(requireContext())

        if (mPurchaseStatus){
            binding.btnPurchase.isClickable = false
            binding.tvBtnPurchase.text = getText(R.string.bought)
        }
        else {
            binding.btnPurchase.isClickable = true
            binding.tvBtnPurchase.text = getText(R.string.buy)
            binding.btnPurchase.setOnClickListener { purchase() }
        }
    }


    //initiate purchase on button click
    private fun purchase() {
        //check if service is already connected
        if (billingClient!!.isReady) {
            initiatePurchase()
        } else {
            billingClient = BillingClient.newBuilder(requireContext()).enablePendingPurchases()
                .setListener(this).build()
            billingClient!!.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        initiatePurchase()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Error " + billingResult.debugMessage,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }

                override fun onBillingServiceDisconnected() {}
            })
        }
    }


    private fun initiatePurchase() {
        val skuList: MutableList<String> = ArrayList()
        skuList.add(PRODUCT_ID)
        val params = SkuDetailsParams.newBuilder()
        params.setSkusList(skuList).setType(SkuType.INAPP)
        billingClient!!.querySkuDetailsAsync(
            params.build()
        ) { billingResult, skuDetailsList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                if (skuDetailsList != null && skuDetailsList.size > 0) {
                    val flowParams = BillingFlowParams.newBuilder()
                        .setSkuDetails(skuDetailsList[0])
                        .build()
                    billingClient!!.launchBillingFlow(requireActivity(), flowParams)
                } else {
                    //try to add item/product id "purchase" inside managed product in google play console
                    Toast.makeText(
                        requireContext(),
                        "Purchase Item not Found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    " Error " + billingResult.debugMessage, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        //if item newly purchased
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            handlePurchases(purchases)
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            val queryAlreadyPurchasesResult = billingClient!!.queryPurchases(SkuType.INAPP)
            val alreadyPurchases = queryAlreadyPurchasesResult.purchasesList
            alreadyPurchases?.let { handlePurchases(it) }
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            Toast.makeText(requireContext(), "Purchase Canceled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                requireContext(),
                "Error " + billingResult.debugMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun handlePurchases(purchases: List<Purchase>) {
        for (purchase in purchases) {
            //if item is purchased
            if (PRODUCT_ID == purchase.sku && purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                if (!verifyValidSignature(purchase.originalJson, purchase.signature)) {
                    // Invalid purchase
                    // show error to user
                    Toast.makeText(
                        requireContext(),
                        "Error : Invalid Purchase",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                // else purchase is valid
                //if item is purchased and not acknowledged
                if (!purchase.isAcknowledged) {
                    val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.purchaseToken)
                        .build()
                    billingClient!!.acknowledgePurchase(acknowledgePurchaseParams, ackPurchase)
                } else {
                    // Grant entitlement to the user on item purchase
                    // restart activity
                    if (!User.getPremiumStatus(requireContext())) {
                        User.setPremiumStatus(true)
                        Toast.makeText(
                            requireContext(),
                            "Item Purchased",
                            Toast.LENGTH_SHORT
                        ).show()
                        requireActivity().recreate()
                    }
                }
            } else if (PRODUCT_ID == purchase.sku && purchase.purchaseState == Purchase.PurchaseState.PENDING) {
                Toast.makeText(
                    requireContext(),
                    "Purchase is Pending. Please complete Transaction", Toast.LENGTH_SHORT
                ).show()
            } else if (PRODUCT_ID == purchase.sku && purchase.purchaseState == Purchase.PurchaseState.UNSPECIFIED_STATE) {
                User.setPremiumStatus(false)
                //=-
                Toast.makeText(
                    requireContext(),
                    "Purchase Status Unknown",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private var ackPurchase =
        AcknowledgePurchaseResponseListener { billingResult ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                //if purchase is acknowledged
                // Grant entitlement to the user. and restart activity
                User.setPremiumStatus(true)
                Toast.makeText(requireContext(), "Item Purchased", Toast.LENGTH_SHORT).show()
                requireActivity().recreate()
            }
        }

    private fun verifyValidSignature(signedData: String, signature: String): Boolean {
        return try {
            // To get key go to Developer Console > Select your app > Development Tools > Services & APIs.
            val base64Key = getString(R.string.base64Key)
            Security.verifyPurchase(base64Key, signedData, signature)
        } catch (e: IOException) {
            false
        }
    }







    // --

    private fun updateTimer(){
        val currentDate = Calendar.getInstance()

        var nextMonth = currentDate[Calendar.MONTH] + 1
        var neededYear = currentDate[Calendar.YEAR]
        // if it's the last month of the year
        if (nextMonth == 12) {
            nextMonth = 0
            neededYear++
        }

        // set event date to the 3th of the next month
        val eventDate = Calendar.getInstance()
        eventDate[Calendar.YEAR] = neededYear
        eventDate[Calendar.MONTH] = nextMonth
        eventDate[Calendar.DAY_OF_MONTH] = 3
        eventDate[Calendar.MINUTE] = 0
        eventDate[Calendar.SECOND] = 0
        eventDate.timeZone = TimeZone.getTimeZone("GMT")

        // find how many milliseconds until the event
        val diff = eventDate.timeInMillis - currentDate.timeInMillis

        // change the milliseconds to days, hours, minutes and seconds
        val days = diff / (24 * 60 * 60 * 1000)
        val hours = diff / (1000 * 60 * 60) % 24
        val minutes = diff / (1000 * 60) % 60
        val seconds = (diff / 1000) % 60

        val text =
            "$days ${getString(R.string.days)} - $hours ${getString(R.string.hours)} - " +
                    "$minutes ${getString(R.string.min)} - $seconds ${getString(R.string.sec)}"

        binding.tvTimer.text = text
    }

    private fun setOnBackPressedCallback(){
        mCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeFragment)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }


    override fun onPause() {
        mCallback.remove()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        setOnBackPressedCallback()

        // Firebase Event
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, ShopFragment().javaClass.simpleName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, ShopFragment().javaClass.simpleName)
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages(null)
    }

    companion object{
        private const val PRODUCT_ID = "purchase"
    }
}