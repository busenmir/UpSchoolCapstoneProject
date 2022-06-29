package com.example.capstoneproject

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.capstoneproject.databinding.ActivitySplashScreenBinding
import com.example.capstoneproject.databinding.FragmentSuccessBinding
import com.example.capstoneproject.login.LoginActivity


class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomanim= AnimationUtils.loadAnimation(this,R.anim.anim)
        binding.gifImage.startAnimation(bottomanim)

        val splashscreenTimeout=5000
        val homeIntent= Intent(this@SplashScreen, LoginActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        },splashscreenTimeout.toLong())


        /*
        val textA = ObjectAnimator.ofFloat(binding.textViewanim,"alpha",1.0f,0.0f).apply {
            duration=4000
        }
        textA.start()
        val background = object : Thread() {
            override fun run() {
                try {
                    binding.gifImage.startAnimation()
                    binding.gifImage.setOnClickListener {
                        val intent = Intent(this@SplashScreen, LoginActivity::class.java)

                        startActivity(intent)
                        finish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()

         */
    }
}