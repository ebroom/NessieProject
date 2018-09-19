package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

class Merchant {

    @SerializedName("address")
    var address : Address = Address()

    @SerializedName("name")
    val name : String? = null

}