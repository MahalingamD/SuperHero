package com.maha.superhero.data.network

import com.maha.superhero.data.model.SearchHeroResult
import com.maha.superhero.data.model.SuperHeroResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebserviceInterface {

    @GET("{characterID}")
    suspend fun getSuperHeroDetail(
            @Path("characterID") aID: String
    ): Response<SuperHeroResult>

    @GET("search/{searchText}")
    suspend fun getSearchSuperHeroDetail(
            @Path("searchText") aID: String
    ): Response<SearchHeroResult>

}