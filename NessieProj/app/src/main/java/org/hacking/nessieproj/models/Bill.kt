package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

data class Bill(
        private val _status : String,
        private val _payee : String,
        private val _nickname : String,
        private val _creation_date : String,
        private val _payment_date : String,
        private val _payment_amount : Int,
        private val _recurring_date : Int,
        private val _upcoming_payment_date : String
) {
    @SerializedName("status")
    val status = _status

    @SerializedName("payee")
    val payee = _payee

    @SerializedName("nickname")
    val nickname = _nickname

    @SerializedName("creation_date")
    val creation_date = _creation_date

    @SerializedName("payment_date")
    val payment_date = _payment_date

    @SerializedName("payment_amount")
    val payment_amount = _payment_amount.toString()

    @SerializedName("recurring_date")
    val recurring_date = _recurring_date.toString()

    @SerializedName("upcoming_payment_date")
    val upcoming_payment_date = _upcoming_payment_date
}