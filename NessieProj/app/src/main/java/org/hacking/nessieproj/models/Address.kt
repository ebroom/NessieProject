package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

data class Address(
        private val _streetNumber : String,
        private val _streetName : String,
        private val _city : String,
        private val _state : String,
        private val _zip : String

) {
    @SerializedName("street_number")
    val streetNumber = _streetNumber

    @SerializedName("street_name")
    val streetName = _streetName

    @SerializedName("city")
    val city = _city

    @SerializedName("state")
    val state = _state

    @SerializedName("zip")
    val zip = _zip
}