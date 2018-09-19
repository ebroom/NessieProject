package org.hacking.nessieproj.models

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.gson.annotations.SerializedName
import org.hacking.nessieproj.BR

class Customer : BaseObservable() {

    @SerializedName("first_name")
    var firstName: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    @SerializedName("last_name")
    var lastName: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }

    @SerializedName("address")
    var address = Address()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.address)
        }

    @SerializedName("_id")
    var customerId = ""
}