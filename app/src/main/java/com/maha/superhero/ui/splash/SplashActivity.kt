package com.maha.superhero.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maha.superhero.R
import com.maha.superhero.ui.search.SearchActivity

class SplashActivity : AppCompatActivity() {


    private val SPLASH_DELAY_TIME: Long = 3000
    lateinit var mSplashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSplashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        mSplashViewModel.splashTimer(SPLASH_DELAY_TIME)

        mSplashViewModel.mSplashLiveData.observe(this,
            Observer<String> {
                if (it.isNotEmpty()) {
                    startActivity(Intent(this, SearchActivity::class.java))
                    this.finish()
                }
            })
    }
}