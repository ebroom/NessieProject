package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

class ATMList() {

    @SerializedName("data")
    val data : List<ATM>? = null

    @SerializedName("paging")
    val paging : Paging? = null

}