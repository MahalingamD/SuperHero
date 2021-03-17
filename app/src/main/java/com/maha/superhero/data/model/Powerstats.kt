package com.maha.superhero.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Powerstats : Serializable {

    @SerializedName("intelligence")
    var intelligence: String? = null

    @SerializedName("strength")
    var strength: String? = null

    @SerializedName("speed")
    var speed: String? = null

    @SerializedName("durability")
    var durability: String? = null

    @SerializedName("power")
    var power: String? = null

    @SerializedName("combat")
    var combat: String? = null
}