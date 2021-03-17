package com.maha.superhero.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Biography : Serializable {

    @SerializedName("full-name")
    var fullname: String? = null

    @SerializedName("alter-egos")
    var alteregos: String? = null

    @SerializedName("aliases")
    var aliases: ArrayList<String> = arrayListOf()

    @SerializedName("place-of-birth")
    var placeofbirth: String? = null

    @SerializedName("first-appearance")
    var firstappearance: String? = null

    @SerializedName("publisher")
    var publisher: String? = null

    @SerializedName("alignment")
    var alignment: String? = null


}