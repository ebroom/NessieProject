package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

class Geocode {

    @SerializedName("lng")
    var lon : Double? = null

    @SerializedName("lat")
    var lat : Double? = null

}