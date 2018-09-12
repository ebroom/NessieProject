package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

data class CustomerAccount(
        private val _accountId: String?,
        private val _type: String?,
        private val _nickname: String?,
        private val _rewards: Int?,
        private val _balance: Int?,
        private val _accountNumber: String?,
        private val _customerId: String?
) {

    @SerializedName("_id")
    var accountId: String? = _accountId

    @SerializedName("type")
    var type: String? = _type

    @SerializedName("nickname")
    var nickname: String? = _nickname

    @SerializedName("rewards")
    var rewards: String? = _rewards.toString()

    @SerializedName("balance")
    var balance: String? = _balance.toString()

    @SerializedName("account_number")
    var accountNumber: String? = _accountNumber

    @SerializedName("customer_id")
    var customerId: String? = _customerId

}