package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

data class Bill(
        private val _status : String,
        private val _payee : String,
        private val _nickname : String,
        private val _creationDate : String,
        private val _paymentDate : String,
        private val _paymentAmount : Double,
        private val _recurringDate : Int,
        private val _upcomingPaymentDate : String
) {
    @SerializedName("status")
    val status = _status

    @SerializedName("payee")
    val payee = _payee

    @SerializedName("nickname")
    val nickname = _nickname

    @SerializedName("creation_date")
    val creationDate = _creationDate

    @SerializedName("payment_date")
    val paymentDate = _paymentDate

    @SerializedName("payment_amount")
    val paymentAmount = _paymentAmount.toString()

    @SerializedName("recurring_date")
    val recurringDate = _recurringDate.toString()

    @SerializedName("upcoming_payment_date")
    val upcomingPaymentDate = _upcomingPaymentDate
}