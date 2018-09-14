package org.hacking.nessieproj.models

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.gson.annotations.SerializedName
import org.hacking.nessieproj.BR

class Address : BaseObservable() {

    @SerializedName("street_number")
    var streetNumber = "1234"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.streetNumber)
        }

    @SerializedName("street_name")
    var streetName = "Street"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.streetName)
        }

    @SerializedName("city")
    var city = "Nowhere"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.city)
        }

    @SerializedName("state")
    var state = "XX"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.state)
        }

    @SerializedName("zip")
    var zip = "12345"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.zip)
        }
}