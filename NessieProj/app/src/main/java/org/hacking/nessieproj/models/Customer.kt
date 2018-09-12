package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

data class Customer(
        private val _firstName : String,
        private val _lastName : String,
        private val _address : Address
) {
    @SerializedName("first_name")
    val firstName = _firstName

    @SerializedName("last_name")
    val lastName = _lastName

    @SerializedName("address")
    val address = _address
}