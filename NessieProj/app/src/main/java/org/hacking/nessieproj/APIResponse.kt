package org.hacking.nessieproj

import com.google.gson.annotations.SerializedName

class APIResponse(code: Int, message: String) {

    @SerializedName("code")
    val code: Int = code

    @SerializedName("message")
    val account: String = message

}