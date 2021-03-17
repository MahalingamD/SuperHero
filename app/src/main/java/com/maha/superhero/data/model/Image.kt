package com.maha.superhero.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image : Serializable {
    @SerializedName("url")
    var url: String? = null
}