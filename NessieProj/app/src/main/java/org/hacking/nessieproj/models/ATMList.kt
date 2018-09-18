package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

class AtmList() {

    @SerializedName("data")
    val data : List<Atm>? = null

    @SerializedName("paging")
    val paging : Paging? = null

}