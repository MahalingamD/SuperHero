package com.maha.superhero.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Appearance : Serializable {

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("race")
    var race: String? = null

    @SerializedName("eye-color")
    var eyecolor: String? = null

    @SerializedName("hair-color")
    var haircolor: String? = null

    @SerializedName("height")
    var height: ArrayList<String> = arrayListOf()

    @SerializedName("weight")
    var weight: ArrayList<String> = arrayListOf()

}