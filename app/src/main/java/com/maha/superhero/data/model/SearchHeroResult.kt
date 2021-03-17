package com.maha.superhero.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SearchHeroResult : Serializable {

    @SerializedName("response")
    var response = ""

    @SerializedName("results-for")
    var resultsfor = ""

    @SerializedName("results")
    var results: ArrayList<SuperHeroResult> = arrayListOf()

}