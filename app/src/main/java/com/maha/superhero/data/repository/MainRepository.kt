package com.maha.superhero.data.repository

import com.maha.superhero.data.model.SearchHeroResult
import com.maha.superhero.data.model.SuperHeroResult
import com.maha.superhero.data.network.ApiClient
import com.maha.superhero.data.network.SafeApiRequest

class MainRepository : SafeApiRequest() {

    suspend fun getSuperHeroDetail(SuperHeroID: String): SuperHeroResult {
        val aApi = ApiClient.getService()

        return apiRequest { aApi.getSuperHeroDetail(SuperHeroID) }
    }

    suspend fun getSearchSuperHeroDetail(aSearchTxt: String): SearchHeroResult {
        val aApi = ApiClient.getService()
        return apiRequest { aApi.getSearchSuperHeroDetail(aSearchTxt) }
    }
}