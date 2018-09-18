package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

class Atm {

    @SerializedName("name")
    var name : String? = null

    @SerializedName("geocode")
    var geocode : Geocode? = null

    @SerializedName("address")
    var address : Address? = null

    @SerializedName("amount_left")
    var amountLeft : Int? = null

}