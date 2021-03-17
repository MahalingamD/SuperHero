package com.maha.superhero.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maha.superhero.data.model.SuperHeroResult
import com.maha.superhero.data.repository.MainRepository
import com.maha.superhero.helper.Coroutines

class SearchViewModel(val mainRepository: MainRepository) : ViewModel() {


    var mSuperHeroLiveDataList = MutableLiveData<ArrayList<SuperHeroResult>>()

    var aSuperHeroLiveData = MutableLiveData<SuperHeroResult>()

    fun callSearchResult(aText: String) {

        Coroutines.main {
            val aSearchresult = mainRepository.getSearchSuperHeroDetail(aText)
            Log.e("aSize", "" + aSearchresult.results.size)
           // if (aSearchresult.response.equals("success", ignoreCase = true)) {
                mSuperHeroLiveDataList.postValue(aSearchresult.results)
           // }else
        }
    }

    fun getSuperHeroDetail(aID: String) {
        Coroutines.main {
            val aResponse = mainRepository.getSuperHeroDetail(aID)
            aSuperHeroLiveData.postValue(aResponse)

        }
    }
}