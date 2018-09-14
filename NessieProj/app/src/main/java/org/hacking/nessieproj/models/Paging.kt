package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

class Paging {

    @SerializedName("previous")
    var previous : String? = null

    @SerializedName("next")
    var next : String? = null

}