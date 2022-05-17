package com.minjaee.beakgame.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.minjaee.beakgame.*
import com.minjaee.beakgame.Information

class AnswerActivity : AppCompatActivity() {
    private lateinit var answerBtn:Button
    private lateinit var answerScore:TextView
    private lateinit var animation:View
    lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        answerBtn = findViewById(R.id.answerBtn)
        answerScore = findViewById(R.id.answerScore)
        animation = findViewById(R.id.answerLottAni)

        Information.myViewModel.currentValue.observe(this) {
            answerScore.text = it.toString()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            animation.visibility = View.INVISIBLE
        }, 2600)
        answerBtnClick()
        getAdMob()
    }

    private fun answerBtnClick() {
        answerBtn.setOnClickListener {
            finish()
        }
    }

    private fun getAdMob() {
        MobileAds.initialize(this) {}

        val adView = AdView(this)
        adView.adUnitId = "ca-app-pub-7394882970303365/8154998012"

        mAdView = findViewById(R.id.answerAdView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}