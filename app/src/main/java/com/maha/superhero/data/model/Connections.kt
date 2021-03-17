package com.maha.superhero.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Connections : Serializable {

    @SerializedName("group-affiliation")
    var groupaff: String? = null

    @SerializedName("relatives")
    var relatives: String? = null
}