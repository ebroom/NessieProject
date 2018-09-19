package org.hacking.nessieproj.models

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.gson.annotations.SerializedName
import org.hacking.nessieproj.BR

class Address : BaseObservable() {

    @SerializedName("street_number")
    var streetNumber = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.streetNumber)
        }

    @SerializedName("street_name")
    var streetName = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.streetName)
        }

    @SerializedName("city")
    var city = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.city)
        }

    @SerializedName("state")
    var state = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.state)
        }

    @SerializedName("zip")
    var zip = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.zip)
        }
}