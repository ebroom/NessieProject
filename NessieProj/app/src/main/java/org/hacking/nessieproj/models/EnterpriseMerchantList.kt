package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

class EnterpriseMerchantList {

    @SerializedName("results")
    var results : List<Merchant>? = null

}