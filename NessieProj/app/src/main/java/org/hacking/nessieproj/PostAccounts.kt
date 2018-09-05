package org.hacking.nessieproj

import com.google.gson.annotations.SerializedName

class PostAccounts(customerId: String?, account: Account) {

    @SerializedName("_id")
    var customerId: String? = customerId

    @SerializedName("body")
    var account: Account? = account

}