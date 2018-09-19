package org.hacking.nessieproj.models

import com.google.gson.annotations.SerializedName

class APIResponse<T> {

    @SerializedName("code")
    val code: Int? = null

    @SerializedName("message")
    val message: String? = null

    @SerializedName("objectCreated")
    val objectCreated: T? = null

}