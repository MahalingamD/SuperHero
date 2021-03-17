package com.maha.superhero.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Work : Serializable {
    @SerializedName("occupation")
    var occupation: String? = null

    @SerializedName("base")
    var base: String? = null
}