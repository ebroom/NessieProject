package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class Deposit {

    @SerializedName("medium")
    val medium = "balance"

    @SerializedName("transaction_date")
    val transactionDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

    @SerializedName("status")
    val status = "pending"

    @SerializedName("description")
    val desc = "Deposit"

    @SerializedName("amount")
    var amount : Double? = 0.0
}