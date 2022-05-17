package com.minjaee.beakgame.view

import android.content.ActivityNotFoundException
import android.content.res.Configuration
import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.minjaee.beakgame.ActionType
import com.minjaee.beakgame.Information
import com.minjaee.beakgame.R
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.link.LinkClient
import com.kakao.sdk.link.WebSharerClient
import com.kakao.sdk.template.model.*
import com.squareup.picasso.Picasso
import java.util.regex.Matcher
import java.util.regex.Pattern

class ResultActivity : AppCompatActivity() {
    private lateinit var resultText:TextView
    private lateinit var resultLink:TextView
    private lateinit var resultScore:TextView
    private lateinit var resultImage:ImageView
    private lateinit var resultBtn:Button
    private lateinit var kakaoBtn:ImageButton
    private var myscore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultText = findViewById(R.id.resultText)
        resultLink = findViewById(R.id.resultLink)
        resultScore = findViewById(R.id.resultScore)
        resultImage = findViewById(R.id.resultIv1)
        resultBtn = findViewById(R.id.resultBtn)
        kakaoBtn = findViewById(R.id.resultKakaoBtn)
        KakaoSdk.init(this, "9410a0fbf66fe85850ca6cb04a559425")

        observeInfo()
        reStartBtnClick()
        linkText()
        kakaoBtnClick()
    }

    private fun reStartBtnClick() {
        resultBtn.setOnClickListener {
            finish()
        }
    }

    private fun kakaoBtnClick() {
        kakaoBtn.setOnClickListener{
            kakaoLink()
        }
    }

    private fun linkText() {
        resultLink.text = Information.result

        val pattern:Pattern = Pattern.compile("여깄어유")
        val transform = Linkify.TransformFilter(object : Linkify.TransformFilter, (Matcher, String) -> String {
                override fun transformUrl(p0: Matcher?, p1: String?): String {
                    return ""
                }

                override fun invoke(p1: Matcher, p2: String): String {
                    return ""
                }

            })


        Linkify.addLinks(resultLink, pattern, Information.link, null, transform)
    }

    private fun observeInfo() {
        Information.myViewModel.currentValue.observe(this) {
            resultScore.text = it.toString()
            myscore = it

            if (it < 2) {
                resultText.text = Information.resultArray[0]
                Picasso.get().load(Information.resultImgArray[0]).into(resultImage)
            } else if (it < 5) {
                resultText.text = Information.resultArray[1]
                Picasso.get().load(Information.resultImgArray[1]).into(resultImage)
            } else if (it < 10) {
                resultText.text = Information.resultArray[2]
                Picasso.get().load(Information.resultImgArray[2]).into(resultImage)
            } else if (it < 15) {
                resultText.text = Information.resultArray[3]
                Picasso.get().load(Information.resultImgArray[3]).into(resultImage)
            } else if (it < 20) {
                resultText.text = Information.resultArray[4]
                Picasso.get().load(Information.resultImgArray[4]).into(resultImage)
            } else {
                resultText.text = Information.resultArray[5]
                Picasso.get().load(Information.resultImgArray[5]).into(resultImage)
            }
        }
    }

    fun kakaoLink() {
        val defaultFeed = FeedTemplate(
            content = Content(
                title = "백종원 게임",
                description = "백종원 게임 해보지 않을래? 내 점수는 " +myscore+ "점 이야",
                imageUrl = "https://ifh.cc/g/dFF618.jpg",
                link = Link(
                    webUrl = "https://developers.kakao.com",
                    mobileWebUrl = "https://developers.kakao.com"
                )
            ),
            buttons = listOf(
                Button(
                    "앱다운 받기",
                    Link(
                        webUrl = "https://www.naver.com",
                        mobileWebUrl = "https://www.naver.com"
                    )
                )
            )
        )

        // 피드 메시지 보내기

        if (LinkClient.instance.isKakaoLinkAvailable(this)) {
            // 카카오톡으로 카카오링크 공유 가능
            LinkClient.instance.defaultTemplate(this, defaultFeed) { linkResult, error ->
                if (error != null) {
                    Log.e("TAG", "카카오링크 보내기 실패", error)
                }
                else if (linkResult != null) {
                    Log.d("TAG", "카카오링크 보내기 성공 ${linkResult.intent}")
                    startActivity(linkResult.intent)

                    // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                    Log.w("TAG", "Warning Msg: ${linkResult.warningMsg}")
                    Log.w("TAG", "Argument Msg: ${linkResult.argumentMsg}")
                }
            }
        } else {
            // 카카오톡 미설치: 웹 공유 사용 권장
            // 웹 공유 예시 코드
            val sharerUrl = WebSharerClient.instance.defaultTemplateUri(defaultFeed)

            // CustomTabs으로 웹 브라우저 열기

            // 1. CustomTabs으로 Chrome 브라우저 열기
            try {
                KakaoCustomTabsClient.openWithDefault(this, sharerUrl)
            } catch(e: UnsupportedOperationException) {
                // Chrome 브라우저가 없을 때 예외처리
            }

            // 2. CustomTabs으로 디바이스 기본 브라우저 열기
            try {
                KakaoCustomTabsClient.open(this, sharerUrl)
            } catch (e: ActivityNotFoundException) {
                // 인터넷 브라우저가 없을 때 예외처리
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        Information.overLabCheck.clear()
        Information.myViewModel.updateValue(actionType = ActionType.RESET)
    }
}