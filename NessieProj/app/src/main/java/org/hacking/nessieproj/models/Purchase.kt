package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class Purchase {

    @SerializedName("merchant_id")
    var merchantId : String = "5ba0052af0cec56abfa439b6"

    @SerializedName("medium")
    val medium = "balance"

    @SerializedName("purchase_date")
    val purchaseDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

    @SerializedName("amount")
    val amount : Double = 5.00

    @SerializedName("status")
    val status = "pending"

    @SerializedName("description")
    val desc = "Pizza!"
}