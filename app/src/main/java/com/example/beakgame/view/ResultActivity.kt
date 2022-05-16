package com.example.beakgame.view

import android.content.res.Configuration
import android.os.Bundle
import android.text.util.Linkify
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.beakgame.ActionType
import com.example.beakgame.Information
import com.example.beakgame.R
import com.squareup.picasso.Picasso
import java.util.regex.Matcher
import java.util.regex.Pattern

class ResultActivity : AppCompatActivity() {
    private lateinit var resultText:TextView
    private lateinit var resultLink:TextView
    private lateinit var resultScore:TextView
    private lateinit var resultImage:ImageView
    private lateinit var resultBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultText = findViewById(R.id.resultText)
        resultLink = findViewById(R.id.resultLink)
        resultScore = findViewById(R.id.resultScore)
        resultImage = findViewById(R.id.resultIv1)
        resultBtn = findViewById(R.id.resultBtn)

        Information.myViewModel.currentValue.observe(this) {
            resultScore.text = it.toString()

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
        reStartBtnClick()
        linkText()
    }

    private fun reStartBtnClick() {
        resultBtn.setOnClickListener {
            finish()
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        Information.overLabCheck.clear()
        Information.myViewModel.updateValue(actionType = ActionType.RESET)
    }
}