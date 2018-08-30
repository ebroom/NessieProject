package org.hacking.nessieproj

import com.google.gson.annotations.SerializedName

class CustomerAccount(accountId: String?, type: String?, nickname: String?, rewards: Int?, balance: Int?, accountNumber: String?,
                      customerId: String?) {

    @SerializedName("_id")
    var accountId: String? = accountId

    @SerializedName("type")
    var type: String? = type

    @SerializedName("nickname")
    var nickname: String? = nickname

    @SerializedName("rewards")
    var rewards: String? = rewards.toString()

    @SerializedName("balance")
    var balance: String? = balance.toString()

    @SerializedName("account_number")
    var accountNumber: String? = accountNumber

    @SerializedName("customer_id")
    var customerId: String? = customerId

}