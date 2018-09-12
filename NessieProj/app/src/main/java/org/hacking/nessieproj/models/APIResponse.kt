package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

data class APIResponse(private val _code: Int, private val _message: String) {

    @SerializedName("code")
    val code: Int = _code

    @SerializedName("message")
    val account: String = _message

}