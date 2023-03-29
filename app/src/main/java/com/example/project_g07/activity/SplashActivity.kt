package com.example.project_g07.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.project_g07.R
import com.example.project_g07.data.HandlePrefs

class SplashActivity : AppCompatActivity() {
    val prefs = HandlePrefs(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val login : Boolean = prefs.getLogin()
        if (login) {
            Handler().postDelayed({
                startActivity(Intent(this, WelcomeBackActivity::class.java))
                finish()
            }, 1000)
        } else {
            Handler().postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 2000)
        }


    }
}