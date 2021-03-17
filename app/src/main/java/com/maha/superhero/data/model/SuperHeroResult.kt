package com.maha.superhero.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SuperHeroResult : Serializable {
    var response = ""
    var error = ""
    var id = ""
    var name = ""

    @SerializedName("powerstats")
    var powerstats: Powerstats? = null

    @SerializedName("biography")
    var biography: Biography? = null

    @SerializedName("appearance")
    var appearance: Appearance? = null

    @SerializedName("work")
    var work: Work? = null

    @SerializedName("connections")
    var connections: Connections? = null

    @SerializedName("image")
    var image: Image? = null

}