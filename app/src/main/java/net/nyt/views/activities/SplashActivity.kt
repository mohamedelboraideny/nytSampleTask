package net.nyt.views.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import net.nyt.nytsample.R
import net.nyt.nytsample.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1000
    private lateinit var mBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        navigateToNextActivity()

    }

    private fun navigateToNextActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, NewsListActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}