package com.sundevs.ihsan.mathbook.view.activities.slpash

import android.os.Bundle
import com.sundevs.ihsan.mathbook.R
import com.sundevs.ihsan.mathbook.view.base.NormalActivity
import android.view.animation.Animation
import android.os.Handler
import android.view.animation.AnimationUtils
import com.sundevs.ihsan.mathbook.view.activities.menu.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.iv_slpash


class SplashScreenActivity : NormalActivity(), Animation.AnimationListener {


    var animBlink: Animation? = null
    val SPLASH_TIME_OUT = 2000

    override fun getActivityView(): Int {
        return R.layout.activity_splash_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen(this)
        animBlink = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.blink
        )
        iv_slpash.startAnimation(animBlink)
        Handler().postDelayed({
            startActivityNoHistory(MainActivity::class.java)
        }, SPLASH_TIME_OUT.toLong())
    }

    override fun isActionBarEnable(): Boolean {
      return false
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {

    }

    override fun onAnimationStart(animation: Animation?) {

    }
}
