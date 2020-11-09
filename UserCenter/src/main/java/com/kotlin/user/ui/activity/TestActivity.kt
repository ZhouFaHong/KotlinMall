package com.kotlin.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.user.R
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
        verticalLayout {
            padding = 30
            editText {
                hint = "Name"
                textSize = 18f

            }

            editText {
                hint = "Pwd"
                textSize = 18f
            }

            button {
                text = "登录"
                onClick {
                    toast(intent.getIntExtra("id",-1).toString())
                }
            }
        }
    }
}