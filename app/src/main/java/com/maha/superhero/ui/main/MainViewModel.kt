package com.maha.superhero.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maha.superhero.data.model.SuperHeroResult
import com.maha.superhero.data.repository.MainRepository
import com.maha.superhero.helper.Coroutines

class MainViewModel(val mainRepository: MainRepository) : ViewModel() {


    var aSuperHeroLiveData = MutableLiveData<SuperHeroResult>()

    fun getSuperHeroDetail(aID: String) {
        Coroutines.main {
            val aResponse = mainRepository.getSuperHeroDetail(aID)
            aSuperHeroLiveData.postValue(aResponse)

        }
    }
}