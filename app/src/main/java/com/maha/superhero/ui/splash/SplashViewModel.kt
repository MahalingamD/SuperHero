package com.maha.superhero.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class SplashViewModel : ViewModel() {


    var mSplash = ""

    var mSplashLiveData = MutableLiveData<String>()

    fun splashTimer(splashDelayTime: Long) {
        runBlocking {
           GlobalScope.launch {
                Dispatchers.Main
                delay(splashDelayTime)
                mSplash = "Splash"
                mSplashLiveData.postValue(mSplash)
            }
        }
    }
}